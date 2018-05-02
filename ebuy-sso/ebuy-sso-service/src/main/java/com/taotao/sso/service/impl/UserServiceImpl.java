package com.ebuy.sso.service.impl;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.common.util.IDUtils;
import com.ebuy.common.util.JsonUtils;
import com.ebuy.dao.TbUserDao;
import com.ebuy.dao.TbUserRegDao;
import com.ebuy.enums.UserStatus;
import com.ebuy.jedis.JedisClient;
import com.ebuy.pojo.TbUser;
import com.ebuy.pojo.TbUserQuery;
import com.ebuy.pojo.TbUserReg;
import com.ebuy.sso.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2017/12/30.
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private TbUserRegDao tbUserRegDao;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Value("${USER_SESSION}")
    private String USER_SESSION;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;
    private static final String EBUY_REG_QUEUE = "queue.ebuy.reg";
    @Override
    public EbuyResult checkData(String data, int type) {
        TbUserQuery tbUserQuery = new TbUserQuery();
        TbUserQuery.Criteria criteria = tbUserQuery.createCriteria();
        //设置查询条件
        //1判断用户名是否可用
        if(type == 1) {
            criteria.andUsernameEqualTo(data);
        }
        //2判断手机号是否可用
        else if (type == 2) {
            criteria.andPhoneEqualTo(data);
        }
        //3判断邮箱是否可用
        else if (type == 3) {
            criteria.andEmailEqualTo(data);
        }
        //执行查询
        List<TbUser> users = tbUserDao.selectByExample(tbUserQuery);
        if(!CollectionUtils.isEmpty(users)) {
            //查询到数据，返回false
            return EbuyResult.ok(false);
        }
        //数据可用
        return EbuyResult.ok(true);
    }

    /**
     * 登录
     * 通过用户名查询用户，再来校验密码。而不是通过用户名和密码一起查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public EbuyResult login(String username, String password) {
        //通过用户名查询用户
        TbUserQuery tbUserQuery = new TbUserQuery();
        TbUserQuery.Criteria criteria = tbUserQuery.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> users = tbUserDao.selectByExample(tbUserQuery);
        if(CollectionUtils.isEmpty(users)) {
            return EbuyResult.build(400, "用户名或密码错误");
        }
        //校验密码是否正确
        TbUser user = users.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return EbuyResult.build(400, "用户名或密码错误");
        }
        //校验用户状态
        int userStatusCode = user.getStatus();
        if(userStatusCode == UserStatus.DELETE.getCode()) {
            return EbuyResult.build(400, "该用户已被删除");
        }
        if (userStatusCode == UserStatus.UNACTIVE.getCode()) {
            return EbuyResult.build(400, "该用户未激活，请点击收信箱的激活链接");
        }

        //生成token
        String token = UUID.randomUUID().toString();
        //清空密码
        user.setPassword(null);
        //将用户信息存储到redis，key是token
        jedisClient.set(USER_SESSION + ":" + token, JsonUtils.objectToJson(user));
        jedisClient.expire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        //登录成功，返回token
        return EbuyResult.ok(token);
    }

    @Override
    public EbuyResult getUserByToken(String token) {
        String userJson = jedisClient.get(USER_SESSION + ":" + token);
        if(StringUtils.isEmpty(userJson)) {
            return EbuyResult.build(400, "用户登录信息已过期");
        }
        //重置session过期时间
        jedisClient.expire(USER_SESSION + ":" + token, SESSION_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(userJson, TbUser.class);
        return EbuyResult.ok(user);
    }




    @Override
    public TbUserReg getByUserName(String username) {
        List<TbUserReg> userRegs = tbUserRegDao.selectByUserName(username);
        //List<TbUserReg> userRegs = tbUserRegDao.listUser();
        if(userRegs != null && userRegs.size() > 0) {
            return userRegs.get(0);
        } else {
            return new TbUserReg();
        }
    }

    @Override
    public int activeUser(String username) {
        return tbUserRegDao.updateStatus(UserStatus.ACTIVE.getCode(), username);
    }

    @Override
    public EbuyResult register(TbUser user) {
        //检查数据的有效性
        if(StringUtils.isEmpty(user.getUsername())) {
            return EbuyResult.build(400, "用户名不能为空");
        }
        //检查数据是否可用
        EbuyResult checkResult = checkData(user.getUsername(), 1);
        if(!(Boolean) checkResult.getData()) {
            return EbuyResult.build(400, "用户名已存在");
        }
        //判断密码是否为空
        if(StringUtils.isEmpty(user.getPassword())) {
           return EbuyResult.build(400, "密码不能为空");
        }
        //校验手机号
        if(!StringUtils.isEmpty(user.getPhone())) {
            checkResult = checkData(user.getPhone(), 2);
            if(!(Boolean) checkResult.getData()) {
                return EbuyResult.build(400, "手机号重复");
            }
        }
        //校验邮箱
        if(!StringUtils.isEmpty(user.getEmail())) {
            checkResult = checkData(user.getEmail(), 3);
            if(!(Boolean) checkResult.getData()) {
                return EbuyResult.build(400, "邮箱重复");
            }
        }
        //校验通过后，补全pojo
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //密码MD5加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        //插入数据
        tbUserDao.insert(user);
        //生成激活码
        String code = IDUtils.getRandomString(8);
        //将code发送给rabbitmq，emialServer消费queue
        sendRegQueue(user.getEmail()+ "_" + user.getUsername() + "_" + code);
        try {
            tbUserRegDao.insert(user.getUsername(), code);
        } catch (Exception e) {
            LOG.error("UserServiceImpl.register.insertUserRegERROR=========",e);
        }

        return EbuyResult.ok();
    }

    private void sendRegQueue(String code) {
        LOG.info("UserServiceImpl.sendRegQueue.code: {}", code);
        try {
            amqpTemplate.convertAndSend(EBUY_REG_QUEUE, code);
        } catch (Exception e) {
            LOG.error("邮件发送失败，code：{}", code);
        }

    }
}

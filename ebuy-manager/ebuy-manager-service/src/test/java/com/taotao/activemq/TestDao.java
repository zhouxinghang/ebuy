package com.taotao.activemq;

import com.ebuy.dao.TbUserDao;
import com.ebuy.dao.TbUserRegDao;
import com.ebuy.pojo.TbUser;
import com.ebuy.pojo.TbUserQuery;
import com.ebuy.pojo.TbUserReg;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhouxinghang on 2018/5/2.
 */
public class TestDao {
    @Autowired
    TbUserRegDao userRegDao;

    @Autowired
    TbUserDao userDao;
    @Test
    public void testUserReg() {
        TbUserReg userReg = new TbUserReg();
        userReg.setUsername("zxh");
        userReg.setCode("helloworld");
        //int result = userRegDao.insert(userReg);
        TbUserQuery tbUserQuery = new TbUserQuery();
        TbUserQuery.Criteria criteria = tbUserQuery.createCriteria();
        List<TbUser> users = userDao.selectByExample(tbUserQuery);
        System.out.println(users.size());
    }

}

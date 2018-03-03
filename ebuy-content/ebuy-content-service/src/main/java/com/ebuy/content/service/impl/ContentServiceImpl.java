package com.ebuy.content.service.impl;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.common.util.JsonUtils;
import com.ebuy.content.service.ContentService;
import com.ebuy.dao.TbContentDao;
import com.ebuy.jedis.JedisClient;
import com.ebuy.pojo.TbContent;
import com.ebuy.pojo.TbContentQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/12/24.
 */

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentDao contentDao;
    @Autowired
    private JedisClient jedisClient; //自动注入的是JedisClientPool，再Spring配置文件中有
    @Value("${INDEX_CONTENT}")
    private String INDEX_CONTENT;

    @Override
    public EbuyResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentDao.insert(content);
        //同步缓存
        //删除对应的缓存信息
        try {
            jedisClient.hdel(INDEX_CONTENT, content.getCategoryId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EbuyResult.ok();
    }

    @Override
    public List<TbContent> getContentByCid(long cid) {
        //先查询缓存
        //用try-catch，为了防止redis不可用了，服务还能正常运行
        try {
            //查询缓存
            String json = jedisClient.hget(INDEX_CONTENT, cid + "");
            //查询到结果，把json转换成List返回
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //缓存中没有命中，需要查询数据库
        TbContentQuery example = new TbContentQuery();
        TbContentQuery.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentDao.selectByExample(example);
        //返回结果
        return list;
    }
}

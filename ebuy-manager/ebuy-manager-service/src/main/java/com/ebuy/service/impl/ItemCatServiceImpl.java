package com.ebuy.service.impl;

import com.ebuy.common.pojo.EasyUITreeNode;
import com.ebuy.dao.TbItemCatDao;
import com.ebuy.pojo.TbItemCat;
import com.ebuy.pojo.TbItemCatQuery;
import com.ebuy.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/24.
 */

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatDao tbItemCatDao;


    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        TbItemCatQuery tbItemCatQuery = new TbItemCatQuery();
        //设置查询条件
        TbItemCatQuery.Criteria criteria = tbItemCatQuery.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> itemCats = tbItemCatDao.selectByExample(tbItemCatQuery);
        //
        List<EasyUITreeNode> treeNodes = new ArrayList<>();
        for(TbItemCat tbItemCat : itemCats) {
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setId(tbItemCat.getId());
            treeNode.setText(tbItemCat.getName());
            treeNode.setState(tbItemCat.getIsParent()? "closed" : "open");
            treeNodes.add(treeNode);
        }

        return treeNodes;
    }

    public TbItemCat getItemCat(long cid) {
        TbItemCatQuery tbItemCatQuery = new TbItemCatQuery();
        //设置查询条件
        TbItemCatQuery.Criteria criteria = tbItemCatQuery.createCriteria();
        criteria.andIdEqualTo(cid);
        List<TbItemCat> itemCats = tbItemCatDao.selectByExample(tbItemCatQuery);
        if(itemCats != null && itemCats.size() > 0) {
            return  itemCats.get(0);
        }
        return new TbItemCat();
    }
}

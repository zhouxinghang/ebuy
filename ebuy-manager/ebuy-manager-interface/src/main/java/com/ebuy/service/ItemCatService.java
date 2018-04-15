package com.ebuy.service;

import com.ebuy.common.pojo.EasyUITreeNode;
import com.ebuy.pojo.TbItem;
import com.ebuy.pojo.TbItemCat;

import java.util.List;

/**
 * Created by admin on 2017/12/24.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);

    TbItemCat getItemCat(long cid);
}

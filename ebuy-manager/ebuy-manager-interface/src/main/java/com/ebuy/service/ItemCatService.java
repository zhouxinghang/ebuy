package com.ebuy.service;

import com.ebuy.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by admin on 2017/12/24.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}

package com.ebuy.content.service;

import com.ebuy.common.pojo.EasyUITreeNode;
import com.ebuy.common.pojo.EbuyResult;

import java.util.List;

/**
 * Created by admin on 2017/12/24.
 */
public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCategoryList(long parentId);

    EbuyResult addContentCategory(Long parentId, String name);
}

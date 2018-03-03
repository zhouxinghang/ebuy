package com.ebuy.controller;

import com.ebuy.common.pojo.EasyUITreeNode;
import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/12/24.
 * 内容分类管理
 */

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @GetMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(
            @RequestParam(value="id", defaultValue="0")Long parentId) {
        return contentCategoryService.getContentCategoryList(parentId);
    }

    @RequestMapping("/create")
    public EbuyResult addContentCategory(Long parentId, String name) {
        EbuyResult result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }
}

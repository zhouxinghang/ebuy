package com.ebuy.controller;

import com.ebuy.common.pojo.EasyUIDataGridResult;
import com.ebuy.common.pojo.EasyUITreeNode;
import com.ebuy.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/12/24.
 * 商品类型控制类
 * item category
 */

@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @PostMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(
            @RequestParam(name = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> treeNodes = itemCatService.getItemCatList(parentId);
        return treeNodes;
    }
}

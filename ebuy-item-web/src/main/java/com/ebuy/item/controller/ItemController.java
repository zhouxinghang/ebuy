package com.ebuy.item.controller;

import com.ebuy.item.pojo.Item;
import com.ebuy.pojo.TbItem;
import com.ebuy.pojo.TbItemCat;
import com.ebuy.pojo.TbItemDesc;
import com.ebuy.service.ItemCatService;
import com.ebuy.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by admin on 2017/12/29.
 * 商品详情页展示
 */
@Controller
public class ItemController {
    private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;
//    @Autowired
//    private ItemCatService itemCatService;

    @GetMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        //取商品基本信息
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);
        //取商品描述
        TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);
        //取商品类目 TODO
        //TbItemCat tbItemCat = itemCatService.getItemCatList(item.getCid());
        //把数据传递给页面
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", tbItemDesc);
        LOG.info("ItemController.showItem.item:{}, itemDesc:{}", item, tbItemDesc);
        //返回逻辑视图
        return "item";
    }
}

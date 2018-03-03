package com.ebuy.service;

import com.ebuy.common.pojo.EasyUIDataGridResult;
import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.pojo.TbItem;
import com.ebuy.pojo.TbItemDesc;

/**
 * Created by admin on 2017/12/23.
 */
public interface ItemService {
    TbItem getItemById(long itemId);

    TbItemDesc getItemDescById(long itemId);

    EasyUIDataGridResult getItemList(int page, int rows);

    EbuyResult addItem(TbItem tbItem, String desc);
}

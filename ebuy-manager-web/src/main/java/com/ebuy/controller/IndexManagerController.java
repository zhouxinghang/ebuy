package com.ebuy.controller;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.search.service.SearchItemService;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/12/26.
 * 索引库维护Controller
 */

@Controller
public class IndexManagerController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/import")
    @ResponseBody
    public EbuyResult importIndex() {
        EbuyResult EbuyResult = searchItemService.importItemsToIndex();
        return EbuyResult;
    }
}

package com.ebuy.controller;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.content.service.ContentService;
import com.ebuy.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/12/24.
 * 内容管理
 */

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/save")
    @ResponseBody
    public EbuyResult addContent(TbContent content) {
        EbuyResult result = contentService.addContent(content);
        return result;
    }
}

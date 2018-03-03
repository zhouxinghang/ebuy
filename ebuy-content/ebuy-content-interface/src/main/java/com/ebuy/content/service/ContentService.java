package com.ebuy.content.service;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.pojo.TbContent;

import java.util.List;

/**
 * Created by admin on 2017/12/24.
 */
public interface ContentService {
    EbuyResult addContent(TbContent content);

    List<TbContent> getContentByCid(long cid);
}

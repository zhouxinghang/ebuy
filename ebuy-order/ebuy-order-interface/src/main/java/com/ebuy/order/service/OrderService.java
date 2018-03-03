package com.ebuy.order.service;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.order.pojo.OrderInfo;

/**
 * Created by admin on 2018/1/2.
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    EbuyResult createOrder(OrderInfo orderInfo);
}

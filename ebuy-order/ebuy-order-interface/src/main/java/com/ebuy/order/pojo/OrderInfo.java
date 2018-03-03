package com.ebuy.order.pojo;

import com.ebuy.pojo.TbOrder;
import com.ebuy.pojo.TbOrderItem;
import com.ebuy.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/1/2.
 */
public class OrderInfo extends TbOrder implements Serializable {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}

package com.ebuy.order.controller;

import com.ebuy.common.pojo.EbuyResult;
import com.ebuy.common.util.CookieUtils;
import com.ebuy.common.util.JsonUtils;
import com.ebuy.order.pojo.OrderInfo;
import com.ebuy.order.service.OrderService;
import com.ebuy.pojo.TbItem;
import com.ebuy.pojo.TbUser;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/2.
 * 订单确认页面处理Controller
 */

@Controller
public class OrderCartController {
    @Autowired
    private OrderService orderService;

    @Value("${CART_KEY}")
    private String CART_KEY;

    @GetMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        //取用户信息，拦截器已将用户信息存入request
        TbUser user = (TbUser) request.getAttribute("user");
        //根据用户信息取收获地址列表，使用静态数据
        List<TbItem> items = getCartItemList(request);
        request.setAttribute("cartList", items);
        return "/order-cart";
    }

    /**
     * 生成订单处理
     * @param orderInfo
     * @param model
     * @return
     */
    @PostMapping("/order/create")
    public String createOrder(OrderInfo orderInfo, Model model) {
        //生成订单
        EbuyResult EbuyResult = orderService.createOrder(orderInfo);
        //返回逻辑视图
        model.addAttribute("orderId", EbuyResult.getData().toString());
        model.addAttribute("payment", orderInfo.getPayment());
        //TODO 预计送达时间，暂定为3天后送达
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
        return "success";
    }



    private List<TbItem> getCartItemList(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, CART_KEY, true);
        if (StringUtils.isEmpty(json)) {
            return new ArrayList<>();
        }
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }
}

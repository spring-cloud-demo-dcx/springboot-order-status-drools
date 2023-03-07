package cn.skuu.service;

import cn.skuu.entity.Order;

import java.util.Map;

/**
 *
 * @author dcx
 * @since 2023-03-07 17:23
 **/
public interface IOrderService {
    //创建新订单
    Order create(Integer id);
    //发起支付
    Order pay(int id);
    //订单发货
    Order deliver(int id);
    //订单收货
    Order receive(int id);
    //获取所有订单信息
    Map<Integer, Order> getOrders();

}

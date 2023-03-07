package cn.skuu.service;

import cn.skuu.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IOrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    void create() {
        Order order = orderService.create();
        orderService.pay(order.getId());
        orderService.deliver(order.getId());
        orderService.receive(order.getId());
        System.out.println(orderService.getOrders());
    }
}
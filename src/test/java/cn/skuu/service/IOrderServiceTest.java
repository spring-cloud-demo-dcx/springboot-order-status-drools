package cn.skuu.service;

import cn.skuu.entity.Order;
import cn.skuu.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IOrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    void create() {
        StopWatch stopWatch = new StopWatch();

        for (int i = 0; i < 100000; i++) {
            stopWatch.start();
            Order order = orderService.create(i);
            orderService.pay(order.getId());
            orderService.deliver(order.getId());
            orderService.receive(order.getId());
            System.out.println(orderService.getOrders().size());
            Assert.isTrue(orderService.getOrders().get(order.getId()).getStatus().equals(OrderStatus.FINISH),"order status error");
            System.out.println(i + "-------------------------------");
            stopWatch.stop();
        }
        System.out.println(orderService.getOrders().values().stream().limit(20));
        System.out.println(stopWatch.getTotalTimeMillis());

    }
}
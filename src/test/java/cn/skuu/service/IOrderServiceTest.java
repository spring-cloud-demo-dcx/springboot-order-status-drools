package cn.skuu.service;

import cn.skuu.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
            Order order = orderService.create((int) (Math.random() * 1000));
            orderService.pay(order.getId());
            orderService.deliver(order.getId());
            orderService.receive(order.getId());
            System.out.println(orderService.getOrders());
            System.out.println(i + "-------------------------------");
            stopWatch.stop();
        }
        System.out.println(stopWatch.getTotalTimeMillis());

    }
}
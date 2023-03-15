package cn.skuu.drools;

import cn.skuu.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRulesTest {

    @Autowired
    private OrderRules orderRules;

    @Test
    void getScore() {
        Order order = new Order();
        order.setPrice(1001);
        orderRules.getScore(order);
        System.out.println(order);
    }

    @Test
    void getUser() {
        orderRules.getUser();
    }
}
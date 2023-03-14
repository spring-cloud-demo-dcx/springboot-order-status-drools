package cn.skuu.drools;

import cn.skuu.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单规则
 *
 * @author dcx
 * @since 2023-03-13 16:27
 **/
@Slf4j
@Service
public class OrderRules {

    @Autowired
    private KieBase kieBase;

    public Order getScore(Order order) {
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }

}

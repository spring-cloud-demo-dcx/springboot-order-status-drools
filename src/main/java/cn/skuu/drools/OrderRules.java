package cn.skuu.drools;

import cn.skuu.entity.Order;
import cn.skuu.entity.User;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private KieSession kieSession;

    public Order getScore(Order order) {
        //fact加入工作内存
        kieSession.insert(order);
        //匹配
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }

    public void getUser() {
        kieSession.insert(User.builder().id(0).userName("user1").build());
        kieSession.insert(User.builder().id(1).userName("user2").build());
        kieSession.insert(User.builder().id(2).userName("user3").build());
        kieSession.fireAllRules();
        QueryResults queryResults = kieSession.getQueryResults("query-2","user3");
        for (QueryResultsRow queryResult : queryResults) {
            User user = (User) queryResult.get("$user");
            log.info("res:{}", user);
        }
        kieSession.dispose();
    }

    private List<User> createUsers() {
        ArrayList<@Nullable User> users =
                Lists.newArrayList();
        users.add(User.builder().id(0).userName("user1").build());
        users.add(User.builder().id(1).userName("user2").build());
        users.add(User.builder().id(2).userName("user3").build());
        return users;
    }

}

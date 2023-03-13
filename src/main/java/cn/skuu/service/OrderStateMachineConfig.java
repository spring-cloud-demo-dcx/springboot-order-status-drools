package cn.skuu.service;

import cn.skuu.entity.Order;
import cn.skuu.enums.OrderStatus;
import cn.skuu.enums.OrderStatusChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 状态机
 *
 * @author dcx
 * @since 2023-03-07 16:52
 **/
@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {
    //暂时存储到本地
    public static final Map<Integer, Order> orderMap = new HashMap<>(100000);

    /**
     * 配置状态
     *
     * @param states
     * @return void
     * @author dcx
     * @date 2023/3/7 16:58
     **/
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states.withStates().initial(OrderStatus.WAIT_PAYMENT).states(EnumSet.allOf(OrderStatus.class));
    }

    /**
     * 状态和事件的关系
     *
     * @param transitions
     * @return void
     * @author dcx
     * @date 2023/3/7 17:00
     **/
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER)
                .event(OrderStatusChangeEvent.PAYED)
                .and()
                .withExternal()
                .source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE)
                .event(OrderStatusChangeEvent.DELIVERY)
                .and()
                .withExternal()
                .source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH)
                .event(OrderStatusChangeEvent.RECEIVED);
    }

    /**
     * 配置状态机id
     *
     * @param configurer 配置器
     * @throws Exception 异常
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderStatusChangeEvent> configurer) throws Exception {
        configurer.withConfiguration()
                .machineId("coordinationStateMachineId");
    }

    /**
     * 持久化配置
     * 在实际使用中，可以配合Redis等进行持久化操作
     * //todo
     *
     * @return
     */
    @Bean
    public DefaultStateMachinePersister persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatus, OrderStatusChangeEvent, Order>() {
            @Override
            public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvent> context, Order order) throws Exception {
                // TODO 注意在这里可以将状态写入id为orderCode的数据库中，实现持久化的目的
                orderMap.put(order.getId(), order);
            }

            @Override
            public StateMachineContext<OrderStatus, OrderStatusChangeEvent> read(Order order) throws Exception {
                // TODO 注意在这个可以从数据库中获取 id为orderCode的业务状态
                //此处直接获取Order中的状态，其实并没有进行持久化读取操作
                System.out.println("获取到的order：" + order);
                return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
            }
        });
    }


}

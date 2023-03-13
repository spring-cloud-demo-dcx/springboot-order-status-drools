package cn.skuu.service.impl;

import cn.skuu.entity.Order;
import cn.skuu.enums.OrderStatus;
import cn.skuu.enums.OrderStatusChangeEvent;
import cn.skuu.service.IOrderService;
import cn.skuu.service.OrderStateMachineConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dcx
 * @since 2023-03-07 17:24
 **/
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persister;

    @Override
    public Order create(Integer id) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        OrderStateMachineConfig.orderMap.put(id, order);
        return order;
    }


    @Override
    public Order pay(int id) {
        Order order = OrderStateMachineConfig.orderMap.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试支付，订单号：" + order);
        Message<OrderStatusChangeEvent> message = MessageBuilder.withPayload(OrderStatusChangeEvent.PAYED).
                setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 支付失败, 状态异常，订单号：" + id);
        }
        return OrderStateMachineConfig.orderMap.get(id);
    }

    @Override
    public Order deliver(int id) {
        Order order = OrderStateMachineConfig.orderMap.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试发货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.DELIVERY)
                .setHeader("order", order).build(), OrderStateMachineConfig.orderMap.get(id))) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 发货失败，状态异常，订单号：" + id);
        }
        return OrderStateMachineConfig.orderMap.get(id);
    }

    @Override
    public Order receive(int id) {
        Order order = OrderStateMachineConfig.orderMap.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试收货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.RECEIVED)
                .setHeader("order", order).build(), OrderStateMachineConfig.orderMap.get(id))) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 收货失败，状态异常，订单号：" + id);
        }
        return OrderStateMachineConfig.orderMap.get(id);
    }

    @Override
    public Map<Integer, Order> getOrders() {
        return OrderStateMachineConfig.orderMap;
    }


    /**
     * 发送订单状态转换事件
     * ！！！需要注意的是，spring提供的状态机是有状态的，需要同步或者使用threadlocal，同步的话会存在性能问题。
     *
     * @param message
     * @param order
     * @return
     */
    private synchronized boolean sendEvent(Message<OrderStatusChangeEvent> message, Order order) {
        boolean result = false;
        try {
            System.out.println("sendEvent开始order：" + order);
            orderStateMachine.startReactively().block();
            // 将会调用read方法将数据库中orderCode记录的状态更新到stateMachine中
            persister.restore(orderStateMachine, order);
            System.out.println("执行之前状态：" + orderStateMachine.getState().getId());
            //添加延迟用于线程安全测试
//            Thread.sleep(1000);
            Mono<Message<OrderStatusChangeEvent>> messageMono = Mono.just(message);
            orderStateMachine.sendEvent(messageMono).doOnComplete(() -> {
                System.out.println("Event handling complete");
            }).subscribe();
            System.out.println("执行之后状态：" + orderStateMachine.getState().getId());
            persister.persist(orderStateMachine, order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stopReactively().block();
        }
        return false;
    }

}

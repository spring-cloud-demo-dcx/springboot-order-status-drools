package cn.skuu.entity;

import cn.skuu.enums.OrderStatus;
import lombok.Data;

/**
 *
 * @author dcx
 * @since 2023-03-07 16:49
 **/
@Data
public class Order {
    private int id;
    private OrderStatus status;
}

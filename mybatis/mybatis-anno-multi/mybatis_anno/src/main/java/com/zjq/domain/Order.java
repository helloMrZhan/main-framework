package com.zjq.domain;



import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>订单</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
@Data
public class Order {

    private int id;
    private LocalDateTime ordertime;
    private double total;

    /**
     * 当前订单属于哪一个用户
     */
    private User user;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordertime=" + ordertime +
                ", total=" + total +
                ", user=" + user +
                '}';
    }
}

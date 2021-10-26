package com.buildoster.service;

import com.buildoster.model.Order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order addOrder(Order order);
    //public int updateOrderStatus(String status,Long id);
    public Optional<Order> getOrderById(Long order_id);
    public List<Order> getAllOrder();
    public List<Order> getAllOrderByUserId(Long user_id);
    public int updateOrder(String order_status,Long id);
}

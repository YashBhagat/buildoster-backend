package com.buildoster.serviceImpl;

import com.buildoster.model.Order;
import com.buildoster.repository.OrderListRepository;
import com.buildoster.repository.OrderRepository;
import com.buildoster.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long order_id) {
        return orderRepository.findById(order_id);
    }

//    @Transactional
//    @Override
//    public int updateOrderStatus(String status,Long id) {
//        return orderRepository.updateOrderStatus(status,id);
//    }

    @Transactional
    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAllOrderDateDesc();
    }

    @Override
    public List<Order> getAllOrderByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public int updateOrder(String order_status,Long id) {
        if (orderRepository.updateOrderStatus(order_status,id) !=0)
            return orderListRepository.updateOrderedItem(order_status, id);
        else
            return 0;
    }
}

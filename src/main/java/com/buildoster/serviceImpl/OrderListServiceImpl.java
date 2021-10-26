package com.buildoster.serviceImpl;

import com.buildoster.model.OrderList;
import com.buildoster.repository.OrderListRepository;
import com.buildoster.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderListServiceImpl implements OrderListService {
    @Autowired
    private OrderListRepository orderListRepository;
    @Override
    public OrderList addOrderList(OrderList orderList) {
        return orderListRepository.save(orderList);
    }

    @Transactional
    @Override
    public int updateOrder(String order_status, Long id) {
        return orderListRepository.updateSingleOrderedItem(order_status, id);
    }
}

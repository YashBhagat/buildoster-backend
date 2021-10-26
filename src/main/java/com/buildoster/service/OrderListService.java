package com.buildoster.service;

import com.buildoster.model.OrderList;

public interface OrderListService {
    public OrderList addOrderList(OrderList orderList);
    public int updateOrder(String order_status,Long id);

}

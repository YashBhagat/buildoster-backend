package com.buildoster.jwtpayload.request;

import com.buildoster.model.OrderList;


public class OrderRequest {
    private String transaction_type;
    private Long user_id;
    private Long address_id;
    private String order_date;
    private OrderList order_list[];

    public OrderRequest(String transaction_type, Long user_id, Long address_id, String order_date, OrderList[] order_list) {
        this.transaction_type = transaction_type;
        this.user_id = user_id;
        this.address_id = address_id;
        this.order_date = order_date;
        this.order_list = order_list;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public OrderList[] getOrder_list() {
        return order_list;
    }

    public void setOrder_list(OrderList[] order_list) {
        this.order_list = order_list;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}

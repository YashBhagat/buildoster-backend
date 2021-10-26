package com.buildoster.repository;

import com.buildoster.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderListRepository extends JpaRepository<OrderList,Long> {
    @Modifying
    @Query("update OrderList ol set ol.order_status = :order_status where ol.order_id = :id")
    public int updateOrderedItem(@Param("order_status") String order_status, @Param("id") Long id);
    @Modifying
    @Query("update OrderList ol set ol.order_status = :order_status where ol.id = :id")
    public int updateSingleOrderedItem(@Param("order_status") String order_status, @Param("id") Long id);
}

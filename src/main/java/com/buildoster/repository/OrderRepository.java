package com.buildoster.repository;

import com.buildoster.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Modifying
    @Query("update Order o set o.order_status = :order_status where id = :id")
    public int updateOrderStatus(@Param("order_status") String order_status,@Param("id") Long id);

    @Query("from Order o order by o.order_date DESC")
    List<Order> findAllOrderDateDesc();
    List<Order> findByUserId(Long userId);
}

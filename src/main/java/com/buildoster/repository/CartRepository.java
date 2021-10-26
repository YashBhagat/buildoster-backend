package com.buildoster.repository;

import com.buildoster.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUserId(Long id);
    int countByUser(Long user_id);
}

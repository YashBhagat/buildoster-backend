package com.buildoster.service;

import com.buildoster.model.Cart;

import java.util.List;

public interface CartService {
    public Cart addCart(Cart cart);
    void deleteCartItem(Long cart_id);
    List<Cart> getAllCartItemsByUserId(Long user_id);
    public int countCartItems(Long user_id);
}

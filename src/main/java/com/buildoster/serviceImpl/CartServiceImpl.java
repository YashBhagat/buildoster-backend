package com.buildoster.serviceImpl;

import com.buildoster.model.Cart;
import com.buildoster.repository.CartRepository;
import com.buildoster.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCartItem(Long cart_id) {
        cartRepository.deleteById(cart_id);
    }

    @Override
    public List<Cart> getAllCartItemsByUserId(Long user_id) {
        return cartRepository.findByUserId(user_id);
    }

    @Override
    public int countCartItems(Long user_id) {
       return cartRepository.countByUser(user_id);
    }
}

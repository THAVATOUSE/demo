package com.example.demo.service;

import com.example.demo.entity.po.Cart;
import com.example.demo.dao.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public synchronized Cart addToCart(String userId, String productId, Integer quantity) {
        // 1. 查询购物车中是否已存在该商品
        Optional<Cart> optionalCart = cartRepository.findByUserIdAndProductId(userId, productId);
        
        if (optionalCart.isPresent()) {
            // 2. 已存在，更新数量
            Cart cartItem = optionalCart.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setUpdateTime(new Date());
            return cartRepository.save(cartItem);
        } else {
            // 3. 不存在，添加新的购物车项
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cartItem.setCreateTime(new Date());
            cartItem.setUpdateTime(new Date());
            return cartRepository.save(cartItem);
        }
    }

    @Override
    public List<Cart> getCartList(String userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public synchronized Cart updateCartItemQuantity(String userId, String productId, Integer quantity) {
        // 1. 查询购物车中是否已存在该商品
        Optional<Cart> optionalCart = cartRepository.findByUserIdAndProductId(userId, productId);
        
        if (optionalCart.isPresent()) {
            // 2. 已存在，更新数量
            Cart cartItem = optionalCart.get();
            cartItem.setQuantity(quantity);
            cartItem.setUpdateTime(new Date());
            return cartRepository.save(cartItem);
        }
        
        return null;
    }

    @Override
    public void deleteCartItem(String userId, String productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}
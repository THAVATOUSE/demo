package com.example.demo.service;

import com.example.demo.entity.po.Cart;

import java.util.List;

public interface CartService {

    // 添加商品到购物车
    Cart addToCart(String userId, String productId, Integer quantity);

    // 获取购物车列表
    List<Cart> getCartList(String userId);

    // 更新购物车商品数量
    Cart updateCartItemQuantity(String userId, String productId, Integer quantity);

    // 删除购物车中的特定商品
    void deleteCartItem(String userId, String productId);

    // 清空购物车
    void clearCart(String userId);
}
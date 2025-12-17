package com.example.demo.dao;

import com.example.demo.entity.po.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    // 根据用户ID查询购物车列表
    List<Cart> findByUserId(String userId);

    // 根据用户ID和商品ID查询特定的购物车项
    Optional<Cart> findByUserIdAndProductId(String userId, String productId);

    // 根据用户ID删除购物车中的所有项
    void deleteByUserId(String userId);

    // 根据用户ID和商品ID删除购物车中的特定项
    void deleteByUserIdAndProductId(String userId, String productId);
}
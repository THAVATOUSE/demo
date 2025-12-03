package com.example.demo.service;

import com.example.demo.entity.po.Product ;
import java.util.List;

public interface ProductService {

    // 发布新产品
    Product addProduct(Product product);

    // 查询某位农户的所有产品
    List<Product> listByFarmer(String farmerId);

    // 查询所有产品 (消费者浏览用)
    List<Product> listAll();

    // 根据ID获取产品详情
    Product getProductById(String id);

    // 扣减库存 (返回 true 表示扣减成功，false 表示库存不足)
    boolean decreaseStock(String productId, Integer quantity);
}

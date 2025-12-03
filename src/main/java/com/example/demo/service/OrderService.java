package com.example.demo.service;

import com.example.demo.entity.po.Order ;
import java.util.List;

public interface OrderService {

    // 创建订单 (核心业务)
    Order createOrder(String customerId, String productId, Integer quantity, String address);

    // 查询买家的订单
    List<Order> getOrdersByCustomer(String customerId);

    // 查询农户的销售订单
    List<Order> getOrdersByFarmer(String farmerId);
}

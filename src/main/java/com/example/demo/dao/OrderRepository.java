package com.example.demo.dao;

import com.example.demo.entity.po.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    // 根据订单编号查询
    Order findByOrderNo(String orderNo);

    // 查询某位消费者的所有订单
    List<Order> findByCustomerId(String customerId);

    // 查询某位农户的所有销售订单
    List<Order> findByFarmerId(String farmerId);
}

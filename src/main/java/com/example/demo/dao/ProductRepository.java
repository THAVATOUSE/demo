package com.example.demo.dao;

import com.example.demo.entity.po.Product ;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    // 查询某位农户发布的所有产品
    List<Product> findByFarmerId(String farmerId);

    // 根据分类查询产品 (用于消费者浏览)
    List<Product> findByCategory(String category);

    // 模糊搜索产品名称
    List<Product> findByNameContaining(String name);
}

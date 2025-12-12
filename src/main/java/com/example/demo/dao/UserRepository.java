package com.example.demo.dao;

import com.example.demo.entity.po.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // 根据用户名查询用户 (用于登录)
    User findByUsername(String username);
    User findByEmail(String email);
}
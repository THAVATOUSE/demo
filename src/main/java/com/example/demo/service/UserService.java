package com.example.demo.service;

import com.example.demo.entity.po.User ;

public interface UserService {

    // 用户注册
    User register(User user);

    // 用户登录
    User login(String identifier, String password);

    // 根据ID获取用户信息
    User getUserById(String id);
}

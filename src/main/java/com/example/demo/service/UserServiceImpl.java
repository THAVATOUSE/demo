package com.example.demo.service;

import com.example.demo.entity.po.User;
import com.example.demo.dao.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        // 1. 检查用户名是否已存在
        User existUser = userRepository.findByUsername(user.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在，请更换！");
        }

        // 2. 补全基础信息
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 3. 保存到数据库
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        // 1. 查询用户
        User user = userRepository.findByUsername(username);

        // 2. 校验是否存在及密码是否正确
        // (注：实际生产中建议使用 BCrypt 加密密码，此处为了演示方便使用明文比对)
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("账号或密码错误！");
        }

        return user;
    }

    @Override
    public User getUserById(String id) {
        // findById 返回的是 Optional，使用 orElse(null) 处理
        return userRepository.findById(id).orElse(null);
    }
}

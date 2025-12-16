package com.example.demo.service;

import com.example.demo.entity.po.User;
import com.example.demo.dao.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.enums.UserRole;
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
        
        // 2. 检查邮箱是否已存在
        existUser = userRepository.findByEmail(user.getEmail());
        if (existUser != null) {
            throw new RuntimeException("邮箱已被注册，请更换！");
        }

        // 3. 补全基础信息
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        
        // 4. 设置默认角色为消费者
        if (user.getRole() == null) {
            user.setRole(UserRole.CUSTOMER);
        }

        // 5. 保存到数据库
        return userRepository.save(user);
    }

    @Override
    public User login(String identifier, String password) {
        User user;
        if (identifier.contains("@")) {
            user = userRepository.findByEmail(identifier);  // 如果含 @，视为邮箱
        } else {
            user = userRepository.findByUsername(identifier);
        }
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

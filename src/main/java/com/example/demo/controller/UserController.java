package com.example.demo.controller;

import com.example.demo.entity.po.User;
import com.example.demo.dao.UserRepository;
import com.example.demo.enums.UserRole;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // 登录
    @PostMapping("/login")
    public Result<User> login(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userService.login(username, password);
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    // 注册
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        try {
            return Result.success(userService.register(user));
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    // 获取用户信息
    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }

    // 编辑基本信息 (只更新phone, email, address)
    @PutMapping("/{id}")
    public Result<User> updateUser(@PathVariable String id, @RequestBody User updateUser) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (updateUser.getPhone() != null) user.setPhone(updateUser.getPhone());
        if (updateUser.getEmail() != null) user.setEmail(updateUser.getEmail());
        if (updateUser.getAddress() != null) user.setAddress(updateUser.getAddress());

        // 可选：手动更新时间（如果审计不生效）
        // user.setUpdateTime(new Date());

        return Result.success(userRepository.save(user));
    }
}
package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.po.User;
import com.example.demo.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")  // 加载 application-test.properties，本地 DB
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testRegisterSuccess() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("123");
        user.setRole(UserRole.CUSTOMER);
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        user.setAddress("Test Address");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        User saved = userService.register(user);
        assertNotNull(saved.getId());
        assertEquals("testuser", saved.getUsername());

        userRepository.delete(saved);  // 清理
    }

    @Test
    void testRegisterFailUsernameExists() {
        User existing = new User();
        existing.setUsername("duplicateuser");
        existing.setPassword("123");
        existing.setRole(UserRole.CUSTOMER);
        userRepository.save(existing);

        User duplicate = new User();
        duplicate.setUsername("duplicateuser");
        duplicate.setPassword("456");
        duplicate.setRole(UserRole.CUSTOMER);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(duplicate));
        assertEquals("用户名已存在，请更换！", exception.getMessage());

        userRepository.delete(existing);  // 清理
    }

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setUsername("loginuser");
        user.setPassword("securepass");
        user.setRole(UserRole.CUSTOMER);
        userRepository.save(user);

        User loggedIn = userService.login("loginuser", "securepass");
        assertNotNull(loggedIn);
        assertEquals("loginuser", loggedIn.getUsername());

        userRepository.delete(user);  // 清理
    }

    @Test
    void testLoginFailWrongPassword() {
        User user = new User();
        user.setUsername("failuser");
        user.setPassword("correctpass");
        user.setRole(UserRole.CUSTOMER);
        userRepository.save(user);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.login("failuser", "wrongpass"));
        assertEquals("账号或密码错误！", exception.getMessage());

        userRepository.delete(user);  // 清理
    }

    @Test
    void testLoginFailUserNotExist() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.login("nonexistent", "pass"));
        assertEquals("账号或密码错误！", exception.getMessage());
    }
}
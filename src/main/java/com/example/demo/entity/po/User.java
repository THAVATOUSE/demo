package com.example.demo.entity.po;

import com.example.demo.enums.UserRole;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
/**
 * 用户实体类
 * 对应集合: users
 * 涵盖：管理员、农户、消费者
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;

    // 用户名，唯一索引
    @Indexed(unique = true)
    private String username;

    private String password;

    // 用户角色：ADMIN, FARMER, CUSTOMER
    private UserRole role;

    private String phone;

    private String email;

    // 联系地址 (用于消费者收货或农户发货)
    private String address;

    // 创建时间 (自动填充)
    @CreatedDate
    private Date createTime;

    // 修改时间 (自动填充)
    @LastModifiedDate
    private Date updateTime;

    // --- Getter & Setter ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

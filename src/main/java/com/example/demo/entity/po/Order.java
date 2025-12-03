package com.example.demo.entity.po;

import com.example.demo.enums.OrderStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
/**
 * 订单实体类
 * 对应集合: orders
 */
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    // 订单编号 (业务唯一标识，非数据库ID)
    @Indexed(unique = true)
    private String orderNo;

    // 购买者 ID
    @Indexed
    private String customerId;

    // 购买的产品 ID
    private String productId;

    // 卖家(农户) ID，方便农户查询自己的订单
    @Indexed
    private String farmerId;

    // 购买数量
    private Integer quantity;

    // 总价
    private Double totalPrice;

    // 配送地址
    private String address;

    // 订单状态 (待支付、配送中、已完成等)
    private OrderStatus status;

    // 负责配送的无人机 ID (如果是无人机配送)
    private String droneId;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    // --- Getter & Setter ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
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

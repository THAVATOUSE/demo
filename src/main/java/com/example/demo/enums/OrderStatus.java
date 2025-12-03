package com.example.demo.enums;
/**
 * 订单状态枚举
 */
public enum OrderStatus {
    PENDING,    // 待支付/待发货
    DELIVERING,   // 配送中 (无人机配送)
    COMPLETED,  // 已完成
    CANCELLED,  // 已取消
    PAID        // 已支付 (待发货)
}
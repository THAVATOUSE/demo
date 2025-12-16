package com.example.demo.controller;

import com.example.demo.entity.po.Order;
import com.example.demo.enums.OrderStatus;
import com.example.demo.service.OrderService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:63343")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单 (立刻购买)
    @PostMapping
    public Result<Order> create(@RequestParam String customerId, @RequestParam String productId,
                                @RequestParam Integer quantity, @RequestParam String address) {
        try {
            return Result.success(orderService.createOrder(customerId, productId, quantity, address));
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    // 消费者查看订单
    @GetMapping("/customer/{customerId}")
    public Result<List<Order>> getByCustomer(@PathVariable String customerId) {
        return Result.success(orderService.getOrdersByCustomer(customerId));
    }

    // 农户查看订单
    @GetMapping("/farmer/{farmerId}")
    public Result<List<Order>> getByFarmer(@PathVariable String farmerId) {
        return Result.success(orderService.getOrdersByFarmer(farmerId));
    }
    
    // 查询订单详情
    @GetMapping("/{orderId}")
    public Result<Order> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return Result.success(order);
        }
        return Result.error("订单不存在");
    }
    
    // 更新订单状态
    @PutMapping("/status/{orderId}")
    public Result<Order> updateStatus(@PathVariable String orderId, @RequestParam OrderStatus status) {
        try {
            return Result.success(orderService.updateOrderStatus(orderId, status));
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
    
    // 取消订单
    @PutMapping("/cancel/{orderId}")
    public Result<Order> cancelOrder(@PathVariable String orderId) {
        try {
            return Result.success(orderService.cancelOrder(orderId));
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
}
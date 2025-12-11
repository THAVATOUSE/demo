package com.example.demo.controller;

import com.example.demo.entity.po.Order;
import com.example.demo.service.OrderService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
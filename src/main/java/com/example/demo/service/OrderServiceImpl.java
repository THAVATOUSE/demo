package com.example.demo.service;

import com.example.demo.enums.DroneStatus;
import com.example.demo.enums.OrderStatus;
import com.example.demo.entity.po.Drone ;
import com.example.demo.entity.po.Order;
import com.example.demo.entity.po.Product;
import com.example.demo.dao.OrderRepository;
import com.example.demo.service.DroneService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private DroneService droneService;

    @Override
    @Transactional // 开启事务 (MongoDB 4.0+ 支持多文档事务，若环境不支持可忽略，逻辑依然有效)
    public Order createOrder(String customerId, String productId, Integer quantity, String address) {

        // 1. 获取产品信息
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("产品不存在");
        }

        // 2. 尝试扣减库存
        boolean success = productService.decreaseStock(productId, quantity);
        if (!success) {
            throw new RuntimeException("库存不足");
        }

        // 4. 计算订单总重量
        Double productWeight = product.getWeight();
        if (productWeight == null) {
            productWeight = 0.1; // 默认重量0.1kg，如果产品未设置重量
        }
        Double orderWeight = productWeight * quantity;
        
        // 5. 分配无人机 (使用智能调度算法)
        Drone drone = droneService.findSuitableDrone(orderWeight);
        if (drone == null) {
            // 如果没有合适的无人机，实际业务可能会进入等待队列，这里简单处理为抛出异常
            throw new RuntimeException("暂无合适的无人机进行配送，请稍后再试");
        }

        // 6. 锁定无人机状态
        droneService.updateStatus(drone.getId(), DroneStatus.BUSY);

        // 7. 创建订单对象
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replace("-", "")); // 生成唯一订单号
        order.setCustomerId(customerId);
        order.setProductId(productId);
        order.setFarmerId(product.getFarmerId()); // 记录是哪个农户卖的
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);
        order.setAddress(address);
        order.setStatus(OrderStatus.DELIVERING); // 状态直接变为配送中
        order.setDroneId(drone.getId());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        // 6. 保存订单
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> getOrdersByFarmer(String farmerId) {
        return orderRepository.findByFarmerId(farmerId);
    }
    
    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
    
    @Override
    @Transactional
    public Order updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        
        // 更新订单状态
        order.setStatus(status);
        order.setUpdateTime(new Date());
        
        // 如果订单完成配送，释放无人机
        if (status == OrderStatus.COMPLETED && order.getDroneId() != null) {
            droneService.updateStatus(order.getDroneId(), DroneStatus.IDLE);
        }
        
        return orderRepository.save(order);
    }
    
    @Override
    @Transactional
    public Order cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        
        // 检查订单是否可以取消
        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("该订单状态不允许取消");
        }
        
        // 更新订单状态为已取消
        order.setStatus(OrderStatus.CANCELLED);
        order.setUpdateTime(new Date());
        
        // 归还库存
        productService.increaseStock(order.getProductId(), order.getQuantity());
        
        // 释放无人机
        if (order.getDroneId() != null) {
            droneService.updateStatus(order.getDroneId(), DroneStatus.IDLE);
        }
        
        return orderRepository.save(order);
    }
}
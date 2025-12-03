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

        // 3. 分配无人机
        Drone drone = droneService.findAvailableDrone();
        if (drone == null) {
            // 如果没有无人机，实际业务可能会进入等待队列，这里简单处理为抛出异常或仅创建订单待调度
            // 演示目的：我们抛出异常，强制要求有无人机才能配送
            throw new RuntimeException("暂无可用无人机进行配送，请稍后再试");
        }

        // 4. 锁定无人机状态
        droneService.updateStatus(drone.getId(), DroneStatus.BUSY);

        // 5. 创建订单对象
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
}
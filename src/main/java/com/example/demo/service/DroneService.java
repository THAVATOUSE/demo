package com.example.demo.service;

import com.example.demo.enums.DroneStatus;
import com.example.demo.entity.po.Drone;
import java.util.List;

public interface DroneService {

    // 注册新无人机
    Drone registerDrone(Drone drone);

    // 获取所有无人机
    List<Drone> getAllDrones();

    // 寻找一架可用的无人机 (状态为 IDLE)
    Drone findAvailableDrone();
    
    // 根据订单重量寻找合适的无人机 (智能调度)
    Drone findSuitableDrone(Double orderWeight);

    // 更新无人机状态
    void updateStatus(String droneId, DroneStatus status);
}
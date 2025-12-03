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

    // 更新无人机状态
    void updateStatus(String droneId, DroneStatus status);
}
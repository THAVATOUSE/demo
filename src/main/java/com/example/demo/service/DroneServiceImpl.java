package com.example.demo.service;

import com.example.demo.enums.DroneStatus ;
import com.example.demo.entity.po.Drone ;
import com.example.demo.dao.DroneRepository ;
import com.example.demo.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public Drone registerDrone(Drone drone) {
        drone.setCreateTime(new Date());
        drone.setUpdateTime(new Date());
        drone.setStatus(DroneStatus.IDLE); // 默认空闲
        return droneRepository.save(drone);
    }

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public Drone findAvailableDrone() {
        // 查询状态为 IDLE 的无人机列表
        List<Drone> idleDrones = droneRepository.findByStatus(DroneStatus.IDLE);
        if (idleDrones != null && !idleDrones.isEmpty()) {
            // 简单策略：返回第一架
            return idleDrones.get(0);
        }
        return null; // 无可用无人机
    }

    @Override
    public void updateStatus(String droneId, DroneStatus status) {
        Drone drone = droneRepository.findById(droneId).orElse(null);
        if (drone != null) {
            drone.setStatus(status);
            drone.setUpdateTime(new Date());
            droneRepository.save(drone);
        }
    }
}

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
    public Drone findSuitableDrone(Double orderWeight) {
        // 查询状态为 IDLE 的无人机列表
        List<Drone> idleDrones = droneRepository.findByStatus(DroneStatus.IDLE);
        if (idleDrones == null || idleDrones.isEmpty()) {
            return null; // 无可用无人机
        }
        
        // 筛选出有足够载重能力的无人机
        List<Drone> suitableDrones = idleDrones.stream()
                .filter(drone -> drone.getCapacity() != null && drone.getCapacity() >= orderWeight)
                .filter(drone -> drone.getBattery() != null && drone.getBattery() > 20) // 电池电量需大于20%
                .collect(java.util.stream.Collectors.toList());
        
        if (suitableDrones.isEmpty()) {
            return null; // 无合适载重的无人机
        }
        
        // 智能调度策略：
        // 1. 优先选择电池电量较高的无人机
        // 2. 其次选择载重能力与订单重量最接近的无人机（负载均衡）
        Drone bestDrone = suitableDrones.stream()
                .sorted((d1, d2) -> {
                    // 首先比较电池电量
                    int batteryCompare = Integer.compare(d2.getBattery(), d1.getBattery());
                    if (batteryCompare != 0) {
                        return batteryCompare;
                    }
                    // 电池电量相同时，选择载重与订单重量最接近的
                    double diff1 = Math.abs(d1.getCapacity() - orderWeight);
                    double diff2 = Math.abs(d2.getCapacity() - orderWeight);
                    return Double.compare(diff1, diff2);
                })
                .findFirst()
                .orElse(null);
        
        return bestDrone;
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

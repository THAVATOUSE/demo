package com.example.demo.dao;

import com.example.demo.enums.DroneStatus ;
import com.example.demo.entity.po.Drone ;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends MongoRepository<Drone, String> {

    // 根据编号查询
    Drone findByCode(String code);

    // 查询特定状态的无人机 (例如找出一台空闲的无人机去送货)
    List<Drone> findByStatus(DroneStatus status);
}

package com.example.demo.controller;

import com.example.demo.entity.po.Drone;
import com.example.demo.enums.DroneStatus;
import com.example.demo.service.DroneService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drone")
public class DroneController {

    @Autowired
    private DroneService droneService;

    // 获取所有无人机
    @GetMapping("/all")
    public Result<List<Drone>> getAll() {
        return Result.success(droneService.getAllDrones());
    }

    // 注册无人机
    @PostMapping
    public Result<Drone> register(@RequestBody Drone drone) {
        return Result.success(droneService.registerDrone(drone));
    }

    // 更新状态
    @PutMapping("/status/{id}")
    public Result<String> updateStatus(@PathVariable String id, @RequestParam DroneStatus status) {
        droneService.updateStatus(id, status);
        return Result.success("更新成功");
    }
}

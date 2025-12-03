package com.example.demo.entity.po;

import com.example.demo.enums.DroneStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
/**
 * 无人机实体类
 * 对应集合: drones
 */
@Document(collection = "drones")
public class Drone {

    @Id
    private String id;

    // 无人机编号 (例如: D-001)
    @Indexed(unique = true)
    private String code;

    // 载重能力 (kg)
    private Double capacity;

    // 电池电量 (%)
    private Integer battery;

    // 当前状态 (空闲、忙碌、维修)
    private DroneStatus status;

    // 当前位置 (模拟坐标或描述)
    private String currentLocation;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    // --- Getter & Setter ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

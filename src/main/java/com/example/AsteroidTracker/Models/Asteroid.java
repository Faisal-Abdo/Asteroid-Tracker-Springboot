package com.example.AsteroidTracker.Models;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Asteroid {
    Integer id;
    String name;
    LocalDateTime startDate; //close approach date to earth
    LocalDateTime endDate;
    Double size;         //measured in diameter
    Long distance;
    Boolean riskOfCollision;
    Double speed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Boolean getRiskOfCollision() {
        return riskOfCollision;
    }

    public void setRiskOfCollision(Boolean riskOfCollision) {
        this.riskOfCollision = riskOfCollision;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}

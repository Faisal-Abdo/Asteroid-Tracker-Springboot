package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.Asteroid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AsteroidDTO {
    Integer id;
    String name;
    LocalDate startDate; //close approach date to earth
    LocalDate endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public static AsteroidDTO convertToDTO(Asteroid asteroid) {
        AsteroidDTO dto = new AsteroidDTO();
        dto.setId(asteroid.getId());
        dto.setDistance(asteroid.getDistance());
        dto.setName(asteroid.getName());
        dto.setSize(asteroid.getSize());
        dto.setSpeed(asteroid.getSpeed());
        dto.setStartDate(asteroid.getStartDate());
        dto.setEndDate(asteroid.getEndDate());
        dto.setRiskOfCollision(asteroid.getRiskOfCollision());
        return dto;
    }

    public static List<AsteroidDTO> convertToDTOList(List<Asteroid> asteroidList) {
        List<AsteroidDTO> asteroidDTOList = new ArrayList<>();
        for (Asteroid asteroid : asteroidList) {
            asteroidDTOList.add(convertToDTO(asteroid));
        }
        return asteroidDTOList;
    }

    public static Asteroid convertFromDTO(AsteroidDTO dto) {
        Asteroid entity = new Asteroid();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDistance(dto.getDistance());
        entity.setSize(dto.getSize());
        entity.setSpeed(dto.getSpeed());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setRiskOfCollision(dto.getRiskOfCollision());
        return entity;
    }

    public static List<Asteroid> convertFromDTOList(List<AsteroidDTO> asteroidDTOList) {
        List<Asteroid> listOfAsteroids = new ArrayList<>();
        for (AsteroidDTO dto : asteroidDTOList) {
            listOfAsteroids.add(convertFromDTO(dto));
        }
        return listOfAsteroids;
    }
}
package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.RelativeVelocity;

import java.util.List;
import java.util.stream.Collectors;

public class RelativeVelocityDTO {

    private Double kilometersPerSecond;
    private Double kilometersPerHour;
    private Double milesPerHour;

    // Getters and Setters
    public Double getKilometersPerSecond() {
        return kilometersPerSecond;
    }

    public void setKilometersPerSecond(Double kilometersPerSecond) {
        this.kilometersPerSecond = kilometersPerSecond;
    }

    public Double getKilometersPerHour() {
        return kilometersPerHour;
    }

    public void setKilometersPerHour(Double kilometersPerHour) {
        this.kilometersPerHour = kilometersPerHour;
    }

    public Double getMilesPerHour() {
        return milesPerHour;
    }

    public void setMilesPerHour(Double milesPerHour) {
        this.milesPerHour = milesPerHour;
    }


    // Convert Single Object to DTO
    public static RelativeVelocityDTO convertToDTO(RelativeVelocity velocity) {
        if (velocity == null) return null;

        RelativeVelocityDTO dto = new RelativeVelocityDTO();
        dto.kilometersPerSecond = velocity.getKilometersPerSecond();
        dto.kilometersPerHour = velocity.getKilometersPerHour();
        dto.milesPerHour = velocity.getMilesPerHour();
        return dto;
    }

    // Convert DTO back to Entity
    public RelativeVelocity convertFromDTO() {
        RelativeVelocity entity = new RelativeVelocity();
        entity.setKilometersPerSecond(kilometersPerSecond);
        entity.setKilometersPerHour(kilometersPerHour);
        entity.setMilesPerHour(milesPerHour);
        return entity;
    }

    // Convert List of RelativeVelocity to List of RelativeVelocityDTO
    public static List<RelativeVelocityDTO> convertListToDTO(List<RelativeVelocity> velocities) {
        if (velocities == null) return null;

        return velocities.stream()
                .map(RelativeVelocityDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert List of RelativeVelocityDTO back to List of RelativeVelocity
    public static List<RelativeVelocity> convertListFromDTO(List<RelativeVelocityDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(RelativeVelocityDTO::convertFromDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "RelativeVelocityDTO{" +
                "kilometersPerSecond=" + kilometersPerSecond +
                ", kilometersPerHour=" + kilometersPerHour +
                ", milesPerHour=" + milesPerHour +
                '}';
    }
}


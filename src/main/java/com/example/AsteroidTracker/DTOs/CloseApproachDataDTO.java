package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.CloseApproachData;

public class CloseApproachDataDTO {

    private String closeApproachDate;

    private MissDistanceDTO missDistance;

    private RelativeVelocityDTO relativeVelocity;

    public String getCloseApproachDate() {
        return closeApproachDate;
    }

    public void setCloseApproachDate(String closeApproachDate) {
        this.closeApproachDate = closeApproachDate;
    }

    public MissDistanceDTO getMissDistance() {
        return missDistance;
    }

    public void setMissDistance(MissDistanceDTO missDistance) {
        this.missDistance = missDistance;
    }

    public RelativeVelocityDTO getRelativeVelocity() {
        return relativeVelocity;
    }

    public void setRelativeVelocity(RelativeVelocityDTO relativeVelocity) {
        this.relativeVelocity = relativeVelocity;
    }

    public static CloseApproachDataDTO convertToDTO(CloseApproachData data) {
        CloseApproachDataDTO dto = new CloseApproachDataDTO();
        dto.closeApproachDate = data.getCloseApproachDate();
        dto.missDistance = MissDistanceDTO.convertToDTO(data.getMissDistance());
        dto.relativeVelocity = RelativeVelocityDTO.convertToDTO(data.getRelativeVelocity());
        return dto;
    }

    public CloseApproachData convertFromDTO() {
        CloseApproachData entity = new CloseApproachData();
        entity.setCloseApproachDate(closeApproachDate);
        entity.setMissDistance(missDistance.convertFromDTO());
        entity.setRelativeVelocity(relativeVelocity.convertFromDTO());
        return entity;
    }
}

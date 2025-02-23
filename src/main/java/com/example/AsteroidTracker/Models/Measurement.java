package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

//@Entity
public class Measurement extends BaseEntity {
    private Double estimatedDiameterMin;

    private Double estimatedDiameterMax;

    public Double getEstimatedDiameterMin() {
        return estimatedDiameterMin;
    }

    @JsonProperty("estimated_diameter_min")
    public void setEstimatedDiameterMin(Double estimatedDiameterMin) {
        this.estimatedDiameterMin = estimatedDiameterMin;
    }

    @JsonProperty("estimated_diameter_max")
    public Double getEstimatedDiameterMax() {
        return estimatedDiameterMax;
    }

    public void setEstimatedDiameterMax(Double estimatedDiameterMax) {
        this.estimatedDiameterMax = estimatedDiameterMax;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "estimatedDiameterMin=" + estimatedDiameterMin +
                ", estimatedDiameterMax=" + estimatedDiameterMax +
                '}';
    }
}

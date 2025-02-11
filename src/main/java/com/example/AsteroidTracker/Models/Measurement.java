package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Measurement {
    @JsonProperty("estimated_diameter_min")
    private Double estimatedDiameterMin;

    @JsonProperty("estimated_diameter_max")
    private Double estimatedDiameterMax;

    public Double getEstimatedDiameterMin() {
        return estimatedDiameterMin;
    }

    public void setEstimatedDiameterMin(Double estimatedDiameterMin) {
        this.estimatedDiameterMin = estimatedDiameterMin;
    }

    public Double getEstimatedDiameterMax() {
        return estimatedDiameterMax;
    }

    public void setEstimatedDiameterMax(Double estimatedDiameterMax) {
        this.estimatedDiameterMax = estimatedDiameterMax;
    }
}

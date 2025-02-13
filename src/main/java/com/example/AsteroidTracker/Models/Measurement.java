package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Measurement {
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
}

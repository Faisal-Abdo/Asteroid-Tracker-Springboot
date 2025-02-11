package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelativeVelocity {
    @JsonProperty("kilometers_per_second")
    private Double kilometersPerSecond;

    @JsonProperty("kilometers_per_hour")
    private Double kilometersPerHour;

    @JsonProperty("miles_per_hour")
    private Double milesPerHour;

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
}

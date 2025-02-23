package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

//@Entity
public class RelativeVelocity extends BaseEntity {
    private Double kilometersPerSecond;

    private Double kilometersPerHour;

    private Double milesPerHour;

    @JsonProperty("kilometers_per_second")
    public Double getKilometersPerSecond() {
        return kilometersPerSecond;
    }

    public void setKilometersPerSecond(Double kilometersPerSecond) {
        this.kilometersPerSecond = kilometersPerSecond;
    }

    @JsonProperty("kilometers_per_hour")
    public Double getKilometersPerHour() {
        return kilometersPerHour;
    }

    public void setKilometersPerHour(Double kilometersPerHour) {
        this.kilometersPerHour = kilometersPerHour;
    }

    @JsonProperty("miles_per_hour")
    public Double getMilesPerHour() {
        return milesPerHour;
    }

    public void setMilesPerHour(Double milesPerHour) {
        this.milesPerHour = milesPerHour;
    }

    @Override
    public String toString() {
        return "RelativeVelocity{" +
                "kilometersPerSecond=" + kilometersPerSecond +
                ", kilometersPerHour=" + kilometersPerHour +
                ", milesPerHour=" + milesPerHour +
                '}';
    }
}

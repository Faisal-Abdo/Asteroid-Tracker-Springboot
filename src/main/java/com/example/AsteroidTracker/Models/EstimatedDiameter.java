package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

//@Entity
public class EstimatedDiameter extends BaseEntity {
    //@OneToOne
    private Measurement kilometers;
    //@OneToOne
    private Measurement meters;
    //@OneToOne
    private Measurement miles;
    //@OneToOne
    private Measurement feet;

    @JsonProperty("kilometers")
    public Measurement getKilometers() {
        return kilometers;
    }

    public void setKilometers(Measurement kilometers) {
        this.kilometers = kilometers;
    }

    @JsonProperty("meters")
    public Measurement getMeters() {
        return meters;
    }

    public void setMeters(Measurement meters) {
        this.meters = meters;
    }

    @JsonProperty("miles")
    public Measurement getMiles() {
        return miles;
    }

    public void setMiles(Measurement miles) {
        this.miles = miles;
    }

    @JsonProperty("feet")
    public Measurement getFeet() {
        return feet;
    }

    public void setFeet(Measurement feet) {
        this.feet = feet;
    }

    @Override
    public String toString() {
        return "EstimatedDiameter{" +
                "kilometers=" + kilometers +
                ", meters=" + meters +
                ", miles=" + miles +
                ", feet=" + feet +
                '}';
    }
}

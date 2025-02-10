package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstimatedDiameter {
    @JsonProperty("kilometers")
    private Measurement kilometers;

    @JsonProperty("meters")
    private Measurement meters;

    @JsonProperty("miles")
    private Measurement miles;

    @JsonProperty("feet")
    private Measurement feet;

    public Measurement getKilometers() {
        return kilometers;
    }

    public void setKilometers(Measurement kilometers) {
        this.kilometers = kilometers;
    }

    public Measurement getMeters() {
        return meters;
    }

    public void setMeters(Measurement meters) {
        this.meters = meters;
    }

    public Measurement getMiles() {
        return miles;
    }

    public void setMiles(Measurement miles) {
        this.miles = miles;
    }

    public Measurement getFeet() {
        return feet;
    }

    public void setFeet(Measurement feet) {
        this.feet = feet;
    }
}

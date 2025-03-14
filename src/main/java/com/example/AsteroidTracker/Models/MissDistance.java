package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

//@Entity
public class MissDistance extends BaseEntity {
    private Double astronomical;

    private Double lunar;

    private Double kilometers;

    private Double miles;


    @JsonProperty("astronomical")
    public Double getAstronomical() {
        return astronomical;
    }

    public void setAstronomical(Double astronomical) {
        this.astronomical = astronomical;
    }

    @JsonProperty("lunar")
    public Double getLunar() {
        return lunar;
    }

    public void setLunar(Double lunar) {
        this.lunar = lunar;
    }

    @JsonProperty("kilometers")
    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    @JsonProperty("miles")
    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    @Override
    public String toString() {
        return "MissDistance{" +
                "astronomical=" + astronomical +
                ", lunar=" + lunar +
                ", kilometers=" + kilometers +
                ", miles=" + miles +
                '}';
    }
}

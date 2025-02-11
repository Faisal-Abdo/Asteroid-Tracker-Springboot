package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MissDistance {
    @JsonProperty("astronomical")
    private Double astronomical;

    @JsonProperty("lunar")
    private Double lunar;

    @JsonProperty("kilometers")
    private Double kilometers;

    @JsonProperty("miles")
    private Double miles;


    public Double getAstronomical() {
        return astronomical;
    }

    public void setAstronomical(Double astronomical) {
        this.astronomical = astronomical;
    }

    public Double getLunar() {
        return lunar;
    }

    public void setLunar(Double lunar) {
        this.lunar = lunar;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }
}

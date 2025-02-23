package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.MissDistance;

import java.util.List;
import java.util.stream.Collectors;

public class MissDistanceDTO {
    private double astronomical;

    private double lunar;

    private double kilometers;

    private double miles;


    public double getAstronomical() {
        return astronomical;
    }

    public void setAstronomical(double astronomical) {
        this.astronomical = astronomical;
    }

    public double getLunar() {
        return lunar;
    }

    public void setLunar(double lunar) {
        this.lunar = lunar;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }


    public static MissDistanceDTO convertToDTO(MissDistance distance) {
        MissDistanceDTO dto = new MissDistanceDTO();
        dto.astronomical = distance.getAstronomical();
        dto.lunar = distance.getLunar();
        dto.kilometers = distance.getKilometers();
        dto.miles = distance.getMiles();
        return dto;
    }

    public MissDistance convertFromDTO() {
        MissDistance entity = new MissDistance();
        entity.setAstronomical(astronomical);
        entity.setLunar(lunar);
        entity.setKilometers(kilometers);
        entity.setMiles(miles);
        return entity;
    }

    public static List<MissDistanceDTO> convertListToDTO(List<MissDistance> distances) {
        if (distances == null) return null;

        return distances.stream()
                .map(MissDistanceDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<MissDistance> convertListFromDTO(List<MissDistanceDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(MissDistanceDTO::convertFromDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MissDistanceDTO{" +
                "astronomical=" + astronomical +
                ", lunar=" + lunar +
                ", kilometers=" + kilometers +
                ", miles=" + miles +
                '}';
    }
}


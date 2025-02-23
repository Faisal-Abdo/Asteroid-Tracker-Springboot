package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.EstimatedDiameter;

import java.util.List;
import java.util.stream.Collectors;

public class EstimatedDiameterDTO {
    private MeasurementDTO kilometers;

    private MeasurementDTO meters;

    private MeasurementDTO miles;

    private MeasurementDTO feet;

    public MeasurementDTO getKilometers() {
        return kilometers;
    }

    public void setKilometers(MeasurementDTO kilometers) {
        this.kilometers = kilometers;
    }

    public MeasurementDTO getMeters() {
        return meters;
    }

    public void setMeters(MeasurementDTO meters) {
        this.meters = meters;
    }

    public MeasurementDTO getMiles() {
        return miles;
    }

    public void setMiles(MeasurementDTO miles) {
        this.miles = miles;
    }

    public MeasurementDTO getFeet() {
        return feet;
    }

    public void setFeet(MeasurementDTO feet) {
        this.feet = feet;
    }

    public static EstimatedDiameterDTO convertToDTO(EstimatedDiameter diameter) {
        EstimatedDiameterDTO dto = new EstimatedDiameterDTO();
        dto.kilometers = MeasurementDTO.convertToDTO(diameter.getKilometers());
        dto.meters = MeasurementDTO.convertToDTO(diameter.getMeters());
        dto.miles = MeasurementDTO.convertToDTO(diameter.getMiles());
        dto.feet = MeasurementDTO.convertToDTO(diameter.getFeet());
        return dto;
    }

    public static EstimatedDiameter convertFromDTO(EstimatedDiameterDTO dto) {
        EstimatedDiameter entity = new EstimatedDiameter();
        entity.setKilometers(MeasurementDTO.convertFromDTO(dto.getKilometers()));
        entity.setMeters(MeasurementDTO.convertFromDTO(dto.getMeters()));
        entity.setMiles(MeasurementDTO.convertFromDTO(dto.getMiles()));
        entity.setFeet(MeasurementDTO.convertFromDTO(dto.getFeet()));
        return entity;
    }

    // Convert List of EstimatedDiameter to List of EstimatedDiameterDTO
    public static List<EstimatedDiameterDTO> convertListToDTO(List<EstimatedDiameter> diameters) {
        if (diameters == null) return null;

        return diameters.stream()
                .map(EstimatedDiameterDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert List of EstimatedDiameterDTO back to List of EstimatedDiameter
    public static List<EstimatedDiameter> convertListFromDTO(List<EstimatedDiameterDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(EstimatedDiameterDTO::convertFromDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "EstimatedDiameterDTO{" +
                "kilometers=" + kilometers +
                ", meters=" + meters +
                ", miles=" + miles +
                ", feet=" + feet +
                '}';
    }
}

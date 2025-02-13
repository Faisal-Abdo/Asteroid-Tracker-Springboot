package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.Measurement;

import java.util.List;
import java.util.stream.Collectors;

public class MeasurementDTO {
    private Double estimatedDiameterMin;
    private Double estimatedDiameterMax;

    public static MeasurementDTO convertToDTO(Measurement measurement) {
        if (measurement == null) return null;

        MeasurementDTO dto = new MeasurementDTO();
        dto.setEstimatedDiameterMin(measurement.getEstimatedDiameterMin());
        dto.setEstimatedDiameterMax(measurement.getEstimatedDiameterMax());
        return dto;
    }

    public static Measurement convertFromDTO(MeasurementDTO dto) {
        if (dto == null) return null;

        Measurement measurement = new Measurement();
        measurement.setEstimatedDiameterMin(dto.getEstimatedDiameterMin());
        measurement.setEstimatedDiameterMax(dto.getEstimatedDiameterMax());
        return measurement;
    }

    public static List<MeasurementDTO> convertListToDTO(List<Measurement> measurements) {
        if (measurements == null) return null;

        return measurements.stream()
                .map(MeasurementDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Measurement> convertListFromDTO(List<MeasurementDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(MeasurementDTO::convertFromDTO)
                .collect(Collectors.toList());
    }

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


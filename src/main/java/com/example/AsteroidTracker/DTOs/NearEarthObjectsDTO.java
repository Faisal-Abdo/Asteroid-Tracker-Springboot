package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.NearEarthObjects;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NearEarthObjectsDTO {
    private Integer id;

    private String neoReferenceId;

    private String name;

    private String nasaJplUrl;

    private double absoluteMagnitudeH;

    private EstimatedDiameterDTO estimatedDiameter;

    private boolean isPotentiallyHazardousAsteroid;

    private List<CloseApproachDataDTO> closeApproachData;

    private boolean isSentryObject;

    private String aiSummary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNeoReferenceId() {
        return neoReferenceId;
    }

    public void setNeoReferenceId(String neoReferenceId) {
        this.neoReferenceId = neoReferenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNasaJplUrl() {
        return nasaJplUrl;
    }

    public void setNasaJplUrl(String nasaJplUrl) {
        this.nasaJplUrl = nasaJplUrl;
    }

    public double getAbsoluteMagnitudeH() {
        return absoluteMagnitudeH;
    }

    public void setAbsoluteMagnitudeH(double absoluteMagnitudeH) {
        this.absoluteMagnitudeH = absoluteMagnitudeH;
    }

    public EstimatedDiameterDTO getEstimatedDiameter() {
        return estimatedDiameter;
    }

    public void setEstimatedDiameter(EstimatedDiameterDTO estimatedDiameter) {
        this.estimatedDiameter = estimatedDiameter;
    }

    public boolean isPotentiallyHazardousAsteroid() {
        return isPotentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(boolean potentiallyHazardousAsteroid) {
        isPotentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    public List<CloseApproachDataDTO> getCloseApproachData() {
        return closeApproachData;
    }

    public void setCloseApproachData(List<CloseApproachDataDTO> closeApproachData) {
        this.closeApproachData = closeApproachData;
    }

    public boolean isSentryObject() {
        return isSentryObject;
    }

    public void setSentryObject(boolean sentryObject) {
        isSentryObject = sentryObject;
    }

    public String getAiSummary() {
        return aiSummary;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }

    // Convert Single Object
    public static NearEarthObjectsDTO convertToDTO(NearEarthObjects obj) {
        if (obj == null) return null;

        NearEarthObjectsDTO dto = new NearEarthObjectsDTO();
        dto.setId(obj.getNearEarthObjectNasaId());
        dto.setNeoReferenceId(obj.getNeoReferenceId());
        dto.setName(obj.getName());
        dto.setNasaJplUrl(obj.getNasaJplUrl());
        dto.setAbsoluteMagnitudeH(obj.getAbsoluteMagnitudeH());
        dto.setEstimatedDiameter(EstimatedDiameterDTO.convertToDTO(obj.getEstimatedDiameter())); // Convert sub-object
        dto.setPotentiallyHazardousAsteroid(obj.getPotentiallyHazardousAsteroid());
        dto.setCloseApproachData(obj.getCloseApproachData().stream()
                .map(CloseApproachDataDTO::convertToDTO)
                .collect(Collectors.toList())); // Convert list
        dto.setSentryObject(obj.getSentryObject());
        dto.setAiSummary(obj.getAiSummary());
        return dto;
    }

    // Convert List of Objects
    public static List<NearEarthObjectsDTO> convertToDTO(List<NearEarthObjects> objects) {
        return objects.stream()
                .map(NearEarthObjectsDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convert Map<String, List<NearEarthObject>>
    public static Map<String, List<NearEarthObjectsDTO>> convertToDTO(Map<String, List<NearEarthObjects>> objectMap) {
        return objectMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> convertToDTO(entry.getValue()) // Convert list inside map
                ));
    }

    public static NearEarthObjects convertFromDTO(NearEarthObjectsDTO dto) {
        NearEarthObjects entity = new NearEarthObjects();

        entity.setNearEarthObjectNasaId(dto.getId());
        entity.setNeoReferenceId(dto.getNeoReferenceId());
        entity.setName(dto.getName());
        entity.setNasaJplUrl(dto.getNasaJplUrl());
        entity.setAbsoluteMagnitudeH(dto.getAbsoluteMagnitudeH());
        entity.setPotentiallyHazardousAsteroid(dto.isPotentiallyHazardousAsteroid());
        entity.setSentryObject(dto.isSentryObject());

        if (dto.getEstimatedDiameter() != null) {
            entity.setEstimatedDiameter(EstimatedDiameterDTO.convertFromDTO(dto.getEstimatedDiameter()));
        }

        if (dto.getCloseApproachData() != null) {
            entity.setCloseApproachData(
                    dto.getCloseApproachData().stream()
                            .map(CloseApproachDataDTO::convertFromDTO)
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }

    @Override
    public String toString() {
        return "{" +
                "isSentryObject=" + isSentryObject +
                ", closeApproachData=" + closeApproachData +
                ", isPotentiallyHazardousAsteroid=" + isPotentiallyHazardousAsteroid +
                ", estimatedDiameter=" + estimatedDiameter +
                ", absoluteMagnitudeH=" + absoluteMagnitudeH +
                ", nasaJplUrl='" + nasaJplUrl + '\'' +
                ", name='" + name + '\'' +
                ", neoReferenceId='" + neoReferenceId + '\'' +
                ", id=" + id +
                '}';
    }
}

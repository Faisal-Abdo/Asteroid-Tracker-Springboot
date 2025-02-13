package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.AsteroidData;

import java.util.List;
import java.util.Map;

public class AsteroidDataDTO {

    private LinksDTO links;

    private int elementCount;

    private Map<String, List<NearEarthObjectsDTO>> nearEarthObjects;

    public LinksDTO getLinks() {
        return links;
    }

    public void setLinks(LinksDTO links) {
        this.links = links;
    }

    public int getElementCount() {
        return elementCount;
    }

    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

    public Map<String, List<NearEarthObjectsDTO>> getNearEarthObjects() {
        return nearEarthObjects;
    }

    public void setNearEarthObjects(Map<String, List<NearEarthObjectsDTO>> nearEarthObjects) {
        this.nearEarthObjects = nearEarthObjects;
    }

    public static AsteroidDataDTO convertToDTO(AsteroidData entity){
        AsteroidDataDTO dto = new AsteroidDataDTO();
        dto.setLinks(LinksDTO.convertToDTO(entity.getLinks()));
        dto.setElementCount(entity.getElementCount());
        dto.setNearEarthObjects(NearEarthObjectsDTO.convertToDTO(entity.getNearEarthObjects()));
        return dto;
    }

}


package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class AsteroidData {
    @JsonProperty("links")
    private Links links;

    @JsonProperty("element_count")
    private Integer elementCount;

    @JsonProperty("near_earth_objects")
    private Map<String, List<NearEarthObjects>> nearEarthObjects;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getElementCount() {
        return elementCount;
    }

    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

    public Map<String, List<NearEarthObjects>> getNearEarthObjects() {
        return nearEarthObjects;
    }

    public void setNearEarthObjects(Map<String, List<NearEarthObjects>> nearEarthObjects) {
        this.nearEarthObjects = nearEarthObjects;
    }
}

package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

//@Entity
public class AsteroidData extends BaseEntity {
    //@OneToOne
    private Links links;
    private Integer elementCount;
    //@OneToOne
    private Map<String, List<NearEarthObjects>> nearEarthObjects;

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("element_count")
    public int getElementCount() {
        return elementCount;
    }

    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

    @JsonProperty("near_earth_objects")
    public Map<String, List<NearEarthObjects>> getNearEarthObjects() {
        return nearEarthObjects;
    }

    public void setNearEarthObjects(Map<String, List<NearEarthObjects>> nearEarthObjects) {
        this.nearEarthObjects = nearEarthObjects;
    }
}

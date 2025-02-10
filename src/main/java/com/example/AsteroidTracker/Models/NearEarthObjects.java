package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NearEarthObjects {
    @JsonProperty("links")
    private Links links;

    @JsonProperty("id")
    private String id;
    
    @JsonProperty("neo_reference_id")
    private String neoReferenceId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nasa_jpl_url")
    private String nasaJplUrl;

    @JsonProperty("absolute_magnitude_h")
    private double absoluteMagnitudeH6;

    @JsonProperty("estimated_diameter")
    private EstimatedDiameter estimatedDiameter;

    @JsonProperty("is_potentially_hazardous_asteroid")
    private Boolean isPotentiallyHazardousAsteroid;

    @JsonProperty("close_approach_data")
    private List<CloseApproachData> closeApproachData;

    @JsonProperty("is_sentry_object")
    private Boolean isSentryObject;

    @JsonProperty("sentry_data")
    private String sentryData;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return absoluteMagnitudeH6;
    }

    public void setAbsoluteMagnitudeH(double absoluteMagnitudeH) {
        this.absoluteMagnitudeH6 = absoluteMagnitudeH;
    }

    public EstimatedDiameter getEstimatedDiameter() {
        return estimatedDiameter;
    }

    public void setEstimatedDiameter(EstimatedDiameter estimatedDiameter) {
        this.estimatedDiameter = estimatedDiameter;
    }

    public boolean isPotentiallyHazardousAsteroid() {
        return isPotentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(boolean potentiallyHazardousAsteroid) {
        this.isPotentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    public List<CloseApproachData> getCloseApproachData() {
        return closeApproachData;
    }

    public void setCloseApproachData(List<CloseApproachData> closeApproachData) {
        this.closeApproachData = closeApproachData;
    }

    public boolean isSentryObject() {
        return isSentryObject;
    }

    public void setSentryObject(boolean sentryObject) {
        this.isSentryObject = sentryObject;
    }

    public double getAbsoluteMagnitudeH6() {
        return absoluteMagnitudeH6;
    }

    public void setAbsoluteMagnitudeH6(double absoluteMagnitudeH6) {
        this.absoluteMagnitudeH6 = absoluteMagnitudeH6;
    }

    public Boolean getPotentiallyHazardousAsteroid() {
        return isPotentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(Boolean potentiallyHazardousAsteroid) {
        isPotentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    public Boolean getSentryObject() {
        return isSentryObject;
    }

    public void setSentryObject(Boolean sentryObject) {
        isSentryObject = sentryObject;
    }

    public String getSentryData() {
        return sentryData;
    }

    public void setSentryData(String sentryData) {
        this.sentryData = sentryData;
    }
}

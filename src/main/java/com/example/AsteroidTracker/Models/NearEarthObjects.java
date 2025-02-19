package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"links", "id", "name", "neoReferenceId", "nasaJplUrl", "absolute_magnitude_h", "estimated_diameter", "is_potentially_hazardous_asteroid", "close_approach_data", "is_sentry_object"})
//@Entity
public class NearEarthObjects extends BaseEntity {
    //@Id
    private Integer nearEarthObjectNasaId;

    //@OneToMany
    private Links links;

    private String neoReferenceId;

    private String name;

    private String nasaJplUrl;

    private double absoluteMagnitudeH;

    //@OneToOne
    private EstimatedDiameter estimatedDiameter;

    private Boolean isPotentiallyHazardousAsteroid;

    //@OneToOne
    private List<CloseApproachData> closeApproachData;

    private Boolean isSentryObject;

    private String sentryData;

    private String aiSummary;

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("id")
    public Integer getNearEarthObjectNasaId() {
        return nearEarthObjectNasaId;
    }

    public void setNearEarthObjectNasaId(Integer nearEarthObjectNasaId) {
        this.nearEarthObjectNasaId = nearEarthObjectNasaId;
    }

    @JsonProperty("neo_reference_id")
    public String getNeoReferenceId() {
        return neoReferenceId;
    }

    public void setNeoReferenceId(String neoReferenceId) {
        this.neoReferenceId = neoReferenceId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("nasa_jpl_url")
    public String getNasaJplUrl() {
        return nasaJplUrl;
    }

    public void setNasaJplUrl(String nasaJplUrl) {
        this.nasaJplUrl = nasaJplUrl;
    }

    @JsonProperty("absolute_magnitude_h")
    public double getAbsoluteMagnitudeH() {
        return absoluteMagnitudeH;
    }

    public void setAbsoluteMagnitudeH(double absoluteMagnitudeH) {
        this.absoluteMagnitudeH = absoluteMagnitudeH;
    }

    @JsonProperty("estimated_diameter")
    public EstimatedDiameter getEstimatedDiameter() {
        return estimatedDiameter;
    }

    public void setEstimatedDiameter(EstimatedDiameter estimatedDiameter) {
        this.estimatedDiameter = estimatedDiameter;
    }

    @JsonProperty("is_potentially_hazardous_asteroid")
    public Boolean getPotentiallyHazardousAsteroid() {
        return isPotentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(Boolean potentiallyHazardousAsteroid) {
        isPotentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    @JsonProperty("close_approach_data")
    public List<CloseApproachData> getCloseApproachData() {
        return closeApproachData;
    }

    public void setCloseApproachData(List<CloseApproachData> closeApproachData) {
        this.closeApproachData = closeApproachData;
    }

    @JsonProperty("is_sentry_object")
    public Boolean getSentryObject() {
        return isSentryObject;
    }

    public void setSentryObject(Boolean sentryObject) {
        isSentryObject = sentryObject;
    }

    @JsonProperty("sentry_data")
    public String getSentryData() {
        return sentryData;
    }

    public void setSentryData(String sentryData) {
        this.sentryData = sentryData;
    }

    public String getAiSummary() {
        return aiSummary;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }
}

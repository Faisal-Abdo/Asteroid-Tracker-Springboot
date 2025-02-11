package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CloseApproachData {
    @JsonProperty("close_approach_date")
    private LocalDate closeApproachDate;

    @JsonProperty("close_approach_date_full")
    private LocalDateTime closeApproachDateFull;

    @JsonProperty("epoch_date_close_approach")
    private Long epochDateCloseApproach;

    @JsonProperty("relative_velocity")
    private RelativeVelocity relativeVelocity;

    @JsonProperty("miss_distance")
    private MissDistance missDistance;

    @JsonProperty("orbiting_body")
    private String orbitingBody;

    public LocalDate getCloseApproachDate() {
        return closeApproachDate;
    }

    public void setCloseApproachDate(LocalDate closeApproachDate) {
        this.closeApproachDate = closeApproachDate;
    }

    public LocalDateTime getCloseApproachDateFull() {
        return closeApproachDateFull;
    }

    public void setCloseApproachDateFull(LocalDateTime closeApproachDateFull) {
        this.closeApproachDateFull = closeApproachDateFull;
    }

    public RelativeVelocity getRelativeVelocity() {
        return relativeVelocity;
    }

    public void setRelativeVelocity(RelativeVelocity relativeVelocity) {
        this.relativeVelocity = relativeVelocity;
    }

    public MissDistance getMissDistance() {
        return missDistance;
    }

    public void setMissDistance(MissDistance missDistance) {
        this.missDistance = missDistance;
    }

    public String getOrbitingBody() {
        return orbitingBody;
    }

    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }

    public Long getEpochDateCloseApproach() {
        return epochDateCloseApproach;
    }

    public void setEpochDateCloseApproach(Long epochDateCloseApproach) {
        this.epochDateCloseApproach = epochDateCloseApproach;
    }
}

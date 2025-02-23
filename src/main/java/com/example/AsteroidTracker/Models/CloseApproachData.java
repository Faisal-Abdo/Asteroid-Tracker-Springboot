package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

//@Entity
public class CloseApproachData extends BaseEntity{
    private String closeApproachDate;

    private String closeApproachDateFull;

    private Long epochDateCloseApproach;

    //@OneToOne
    private RelativeVelocity relativeVelocity;

    //@OneToOne
    private MissDistance missDistance;

    private String orbitingBody;

    @JsonProperty("close_approach_date")
    public String getCloseApproachDate() {
        return closeApproachDate;
    }

    public void setCloseApproachDate(String closeApproachDate) {
        this.closeApproachDate = closeApproachDate;
    }

    @JsonProperty("close_approach_date_full")
    public String getCloseApproachDateFull() {
        return closeApproachDateFull;
    }

    public void setCloseApproachDateFull(String closeApproachDateFull) {
        this.closeApproachDateFull = closeApproachDateFull;
    }

    @JsonProperty("relative_velocity")
    public RelativeVelocity getRelativeVelocity() {
        return relativeVelocity;
    }

    public void setRelativeVelocity(RelativeVelocity relativeVelocity) {
        this.relativeVelocity = relativeVelocity;
    }

    @JsonProperty("miss_distance")
    public MissDistance getMissDistance() {
        return missDistance;
    }

    public void setMissDistance(MissDistance missDistance) {
        this.missDistance = missDistance;
    }

    @JsonProperty("orbiting_body")
    public String getOrbitingBody() {
        return orbitingBody;
    }

    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }

    @JsonProperty("epoch_date_close_approach")
    public Long getEpochDateCloseApproach() {
        return epochDateCloseApproach;
    }

    public void setEpochDateCloseApproach(Long epochDateCloseApproach) {
        this.epochDateCloseApproach = epochDateCloseApproach;
    }

    @Override
    public String toString() {
        return "CloseApproachData{" +
                "closeApproachDate='" + closeApproachDate + '\'' +
                ", closeApproachDateFull='" + closeApproachDateFull + '\'' +
                ", epochDateCloseApproach=" + epochDateCloseApproach +
                ", relativeVelocity=" + relativeVelocity +
                ", missDistance=" + missDistance +
                ", orbitingBody='" + orbitingBody + '\'' +
                '}';
    }
}

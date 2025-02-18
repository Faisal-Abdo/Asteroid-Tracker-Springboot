package com.example.AsteroidTracker.Models;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

public class BaseEntity {

    private Boolean isActive;

    @CreationTimestamp
    private LocalDate createdDate;

    private LocalDate updatedDate;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}
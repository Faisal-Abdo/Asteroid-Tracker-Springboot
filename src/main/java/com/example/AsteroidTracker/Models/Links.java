package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@Entity
public class Links extends BaseEntity {

    private String next;

    private String previous;

    private String self;

    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @JsonProperty("previous")
    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    @JsonProperty("self")
    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "Links{" +
                "next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", self='" + self + '\'' +
                '}';
    }
}

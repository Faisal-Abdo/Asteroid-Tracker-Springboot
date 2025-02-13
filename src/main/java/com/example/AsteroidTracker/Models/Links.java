package com.example.AsteroidTracker.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Links {

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
}

package com.example.AsteroidTracker.DTOs;

import com.example.AsteroidTracker.Models.Links;
import org.springframework.boot.actuate.endpoint.web.Link;

import java.util.ArrayList;
import java.util.List;

public class LinksDTO {

    private String next;

    private String previous;

    private String self;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public static LinksDTO convertToDTO(Links entity) {
        LinksDTO dto = new LinksDTO();
        dto.setSelf(entity.getSelf());
        dto.setPrevious(entity.getPrevious());
        dto.setNext(entity.getNext());
        return dto;
    }

    public static List<LinksDTO> convertToDTO(List<Links> entitiesList) {
        List<LinksDTO> linksDTOList = new ArrayList<>();
        for (Links entity : entitiesList) {
            linksDTOList.add(convertToDTO(entity));
        }
        return linksDTOList;
    }
}

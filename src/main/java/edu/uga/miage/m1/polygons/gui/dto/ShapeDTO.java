package edu.uga.miage.m1.polygons.gui.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ShapeDTO {
    @JsonIgnoreProperties
    @JsonProperty
    private String type;
    @JsonProperty
    private int x;
    @JsonProperty
    private int y;
}

package edu.uga.miage.m1.polygons.gui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ShapeDTO {
    @JsonProperty
    private AttributesDTO shape;
}

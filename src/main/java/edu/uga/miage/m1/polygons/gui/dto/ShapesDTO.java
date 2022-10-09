package edu.uga.miage.m1.polygons.gui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ShapesDTO {
    @JsonProperty
    private List<ShapeDTO> shapes;
}

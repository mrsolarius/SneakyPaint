package edu.uga.miage.m1.polygons.gui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ShapesDTO {

    ShapesDTO(){}
    public ShapesDTO(List<ShapeDTO> shapes) {
        this.shapes = shapes;
    }

    @JsonProperty(value = "shapes",required = true)
    private List<ShapeDTO> shapes;
}

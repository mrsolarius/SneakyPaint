package edu.uga.miage.m1.polygons.gui.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ShapesDTO {

    ShapesDTO(){}
    public ShapesDTO(List<ShapeDTO> shapes) {
        this.shapes = shapes;
    }

    private List<ShapeDTO> shapes;
}

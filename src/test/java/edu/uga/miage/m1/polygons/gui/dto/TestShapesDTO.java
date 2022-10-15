package edu.uga.miage.m1.polygons.gui.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TestShapesDTO {

    @Test
    void testExists(){
        ShapeDTO shapeDTO = new ShapeDTO("circle", 10, 10);
        ShapeDTO shapeDTO2 = new ShapeDTO("square", 20, 20);
        ShapeDTO shapeDTO3 = new ShapeDTO("triangle", 30, 30);
        ShapesDTO shapesDTO = new ShapesDTO(List.of(shapeDTO, shapeDTO2, shapeDTO3));
        assert (shapesDTO.getShapes() != null);
        assert (shapesDTO.getShapes().size() == 3);
        assert (shapesDTO.getShapes().get(0).getType().equals("circle"));
        assert (shapesDTO.getShapes().get(0).getX() == 10);
        assert (shapesDTO.getShapes().get(0).getY() == 10);
        assert (shapesDTO.getShapes().get(1).getType().equals("square"));
        assert (shapesDTO.getShapes().get(1).getX() == 20);
        assert (shapesDTO.getShapes().get(1).getY() == 20);
        assert (shapesDTO.getShapes().get(2).getType().equals("triangle"));
        assert (shapesDTO.getShapes().get(2).getX() == 30);
        assert (shapesDTO.getShapes().get(2).getY() == 30);
    }
}

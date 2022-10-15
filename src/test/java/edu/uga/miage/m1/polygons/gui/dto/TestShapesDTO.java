package edu.uga.miage.m1.polygons.gui.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestShapesDTO {

    @Test
    void testExists(){
        ShapeDTO shapeDTO = new ShapeDTO("circle", 10, 10);
        ShapeDTO shapeDTO2 = new ShapeDTO("square", 20, 20);
        ShapeDTO shapeDTO3 = new ShapeDTO("triangle", 30, 30);
        ShapesDTO shapesDTO = new ShapesDTO(List.of(shapeDTO, shapeDTO2, shapeDTO3));
        assertNotNull (shapesDTO.getShapes());
        assertEquals (3,shapesDTO.getShapes().size());
        assertEquals ("circle",shapesDTO.getShapes().get(0).getType());
        assertEquals (10,shapesDTO.getShapes().get(0).getX());
        assertEquals (10,shapesDTO.getShapes().get(0).getY());
        assertEquals ("square",shapesDTO.getShapes().get(1).getType());
        assertEquals (20,shapesDTO.getShapes().get(1).getX());
        assertEquals (20,shapesDTO.getShapes().get(1).getY());
        assertEquals ("triangle",shapesDTO.getShapes().get(2).getType());
        assertEquals (30,shapesDTO.getShapes().get(2).getX());
        assertEquals (30,shapesDTO.getShapes().get(2).getY());
    }
}

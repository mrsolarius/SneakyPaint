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
        assertEquals (shapesDTO.getShapes().size(),3);
        assertEquals (shapesDTO.getShapes().get(0).getType(),"circle");
        assertEquals (shapesDTO.getShapes().get(0).getX(),10);
        assertEquals (shapesDTO.getShapes().get(0).getY(),10);
        assertEquals (shapesDTO.getShapes().get(1).getType(),"square");
        assertEquals (shapesDTO.getShapes().get(1).getX(),20);
        assertEquals (shapesDTO.getShapes().get(1).getY(),20);
        assertEquals (shapesDTO.getShapes().get(2).getType(),"triangle");
        assertEquals (shapesDTO.getShapes().get(2).getX(),30);
        assertEquals (shapesDTO.getShapes().get(2).getY(),30);
    }
}

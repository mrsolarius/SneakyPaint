package edu.uga.miage.m1.polygons.gui.dto;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestShapeDTO {

    @Test
    void testToEntityCircle() {
        ShapeDTO shapeDTO = new ShapeDTO("circle", 10, 10);
        SimpleShape shape = shapeDTO.toEntity();
        assertNotNull (shape);
        assertInstanceOf(Circle.class, shape);
        assertEquals (shape.getX(),10);
        assertEquals (shape.getY(),10);
    }

    @Test
    void testToEntitySquare() {
        ShapeDTO shapeDTO = new ShapeDTO("square", 20, 20);
        SimpleShape shape = shapeDTO.toEntity();
        assertNotNull (shape);
        assertInstanceOf(Square.class, shape);
        assertEquals (shape.getX(),20);
        assertEquals (shape.getY(),20);
    }

    @Test
    void testToEntityTriangle() {
        ShapeDTO shapeDTO = new ShapeDTO("triangle", 30, 30);
        SimpleShape shape = shapeDTO.toEntity();
        assertNotNull (shape );
        assertInstanceOf(Triangle.class, shape);
        assertEquals (shape.getX(),30);
        assertEquals (shape.getY(),30);
    }

    @Test
    void testToUnknownEntity() {
        ShapeDTO shapeDTO = new ShapeDTO("unknown", 30, 30);
        assertThrows(IllegalArgumentException.class, shapeDTO::toEntity);
    }
}

package edu.uga.miage.m1.polygons.gui.dto;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestShapeDTO {

    @Test
    void testToEntityCircle() {
        ShapeDTO shapeDTO = new ShapeDTO("circle", 10, 10);
        SimpleShape shape = shapeDTO.toEntity();
        assert (shape != null);
        assert (shape instanceof Circle);
        assert (shape.getX() == 10);
        assert (shape.getY() == 10);
    }

    @Test
    void testToEntitySquare() {
        ShapeDTO shapeDTO = new ShapeDTO("square", 20, 20);
        SimpleShape shape = shapeDTO.toEntity();
        assert (shape != null);
        assert (shape.getX() == 20);
        assert (shape.getY() == 20);
    }

    @Test
    void testToEntityTriangle() {
        ShapeDTO shapeDTO = new ShapeDTO("triangle", 30, 30);
        SimpleShape shape = shapeDTO.toEntity();
        assert (shape != null);
        assert (shape.getX() == 30);
        assert (shape.getY() == 30);
    }

    @Test
    void testToUnknownEntity() {
        ShapeDTO shapeDTO = new ShapeDTO("unknown", 30, 30);
        assertThrows(IllegalArgumentException.class, shapeDTO::toEntity);
    }
}

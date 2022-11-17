package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSquare {
    @Test
    void testSquare() {
        Square s = ShapeFactory.createSquare(null,10, 10);
        assertEquals(10,s.getX());
        assertEquals(10,s.getY());
    }
}

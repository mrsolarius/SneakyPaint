package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCircle {
    @Test
    void testCircle() {
        Circle c = ShapeFactory.createCircle(10, 10);
        assertEquals(10,c.getX());
        assertEquals( 10,c.getY());
    }
}

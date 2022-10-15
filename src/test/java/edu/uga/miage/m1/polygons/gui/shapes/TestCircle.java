package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCircle {
    @Test
    void testCircle() {
        Circle c = new Circle(10, 10);
        assertEquals(c.getX(),10);
        assertEquals(c.getY(), 10);
    }
}

package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTriangle {
    @Test
    void testTriangle() {
        Triangle t = new Triangle(10, 10);
        assertEquals(t.getX(),10);
        assertEquals(t.getY(), 10);
    }
}

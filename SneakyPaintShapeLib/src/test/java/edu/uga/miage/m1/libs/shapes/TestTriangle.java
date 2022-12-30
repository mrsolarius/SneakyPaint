package edu.uga.miage.m1.libs.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTriangle {
    @Test
    void testTriangle() {
        Triangle t = ShapeFactory.createTriangle(10, 10);
        assertEquals(10,t.getX());
        assertEquals(10,t.getY());
    }
}

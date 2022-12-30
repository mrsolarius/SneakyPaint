package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestAbstractShape {

    @Test
    void shouldBeInsideOfAbstractShape() {
        Circle c = ShapeFactory.createCircle(10, 10);
        assertTrue(c.isInside(10, 10));
    }

    @Test
    void shouldBeInsideAtTheEdgeOfAbstractShape() {
        Circle c = ShapeFactory.createCircle(10, 10);
        assertTrue(c.isInside(c.getWidth()+c.getX(), c.getHeight()+c.getY()));
    }
}

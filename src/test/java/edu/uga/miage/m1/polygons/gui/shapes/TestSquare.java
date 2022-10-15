package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSquare {
    @Test
    void testSquare() {
        Square s = new Square(10, 10);
        assertEquals(s.getX(),10);
        assertEquals(s.getY(),10);
    }
}

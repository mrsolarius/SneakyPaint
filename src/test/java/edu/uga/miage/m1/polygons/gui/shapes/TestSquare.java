package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

class TestSquare {
    @Test
    void testSquare() {
        Square s = new Square(10, 10);
        assert (s.getX() == 10);
        assert (s.getY() == 10);
    }
}

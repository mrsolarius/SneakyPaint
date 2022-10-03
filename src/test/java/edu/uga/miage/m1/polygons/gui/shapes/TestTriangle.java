package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

class TestTriangle {
    @Test
    void testTriangle() {
        Triangle t = new Triangle(10, 10);
        assert (t.getX() == 10);
        assert (t.getY() == 10);
    }
}

package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

class TestCircle {
    @Test
    void testCircle() {
        Circle c = new Circle(10, 10);
        assert (c.getX() == 10);
        assert (c.getY() == 10);
    }
}

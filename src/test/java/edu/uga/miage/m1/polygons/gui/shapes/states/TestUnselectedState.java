package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestUnselectedState {
    private SimpleShape shape;

    @BeforeEach
    void setUp() {
        shape = ShapeFactory.createCircle(20, 30);
        shape.getState().unselect();
    }

    @Test
    void shouldNotMoveObject() {
        int currentX = shape.getX();
        int currentY = shape.getY();
        shape.getState().move(10, 15);
        assertInstanceOf(UnselectedState.class, shape.getState());
        assertEquals(currentX, shape.getX());
        assertEquals(currentY, shape.getY());
    }

    @Test
    void shouldNotResizeObject() {
        int currentWidth = shape.getWidth();
        int currentHeight = shape.getHeight();
        shape.getState().resize(10, 10);
        assertInstanceOf(UnselectedState.class, shape.getState());
        assertEquals(currentWidth, shape.getWidth());
        assertEquals(currentHeight, shape.getHeight());
    }

    @Test
    void shouldNotEditElevation() {
        int currentElevation = shape.getElevation();
        shape.getState().editElevation(10);
        assertInstanceOf(UnselectedState.class, shape.getState());
        assertEquals(currentElevation, shape.getElevation());
    }

    @Test
    void shouldNotGroupObject() {
        shape.getState().group();
        assertInstanceOf(UnselectedState.class, shape.getState());
    }

    @Test
    void shouldNotUngroupObject() {
        shape.getState().ungroup();
        assertInstanceOf(UnselectedState.class, shape.getState());
    }

    @Test
    void shouldSelectObject() {
        shape.getState().select();
        assertInstanceOf(SelectedState.class, shape.getState());
        assertTrue(shape.isSelected());
    }

    @Test
    void shouldUnselectObject() {
        shape.getState().unselect();
        assertInstanceOf(UnselectedState.class, shape.getState());
        assertFalse(shape.isSelected());
    }


}

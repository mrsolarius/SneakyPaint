package edu.uga.miage.m1.libs.shapes.states;

import edu.uga.miage.m1.libs.shapes.ShapeFactory;
import edu.uga.miage.m1.libs.shapes.SimpleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestSelectedState {
    private SimpleShape shape;

    @BeforeEach
    void setUp() {
        shape = ShapeFactory.createCircle(20, 30);
        shape.getState().select();
    }

    @Test
    void shouldMoveObject() {
        int currentX = shape.getX();
        int currentY = shape.getY();
        shape.getState().move(10, 15);
        assertInstanceOf(SelectedState.class, shape.getState());
        assertEquals(currentX+10, shape.getX());
        assertEquals(currentY+15, shape.getY());
    }

    @Test
    void shouldEditElevation() {
        int currentElevation = shape.getElevation();
        shape.getState().editElevation(10);
        assertInstanceOf(SelectedState.class, shape.getState());
        assertEquals(currentElevation+10, shape.getElevation());
    }

    @Test
    void shouldGroupObject() {
        shape.getState().group();
        assertInstanceOf(GroupState.class, shape.getState());
    }

    @Test
    void shouldUngroupObject() {
        shape.getState().ungroup();
        assertInstanceOf(SelectedState.class, shape.getState());
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

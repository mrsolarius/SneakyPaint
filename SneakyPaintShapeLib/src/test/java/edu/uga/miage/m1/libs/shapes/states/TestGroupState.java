package edu.uga.miage.m1.libs.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class TestGroupState {
    private SimpleShape shape;

    @BeforeEach
    void setUp() {
        shape = ShapeFactory.createCircle(20, 30);
        shape.getState().select();
        shape.getState().group();
    }

    @Test
    void shouldMoveObject() {
        int currentX = shape.getX();
        int currentY = shape.getY();
        shape.getState().move(10, 15);
        assertInstanceOf(GroupState.class, shape.getState());
        assertEquals(currentX+10, shape.getX());
        assertEquals(currentY+15, shape.getY());
    }

    @Test
    void shouldEditElevation() {
        int currentElevation = shape.getElevation();
        shape.getState().editElevation(10);
        assertInstanceOf(GroupState.class, shape.getState());
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
        assertTrue(shape.isSelected());
    }

    @Test
    void shouldNotSelectObject() {
        shape.getState().select();
        assertInstanceOf(GroupState.class, shape.getState());
        assertFalse(shape.isSelected());
    }

    @Test
    void shouldNotUnselectObject() {
        shape.getState().unselect();
        assertInstanceOf(GroupState.class, shape.getState());
        assertFalse(shape.isSelected());
    }


}

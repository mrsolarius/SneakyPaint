package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAddCircleState {

    private WhiteBoard whiteBoard;

    @BeforeEach
    void setUp() {
        whiteBoard = WhiteBoard.getInstance();
        whiteBoard.setState(new SelectMode(whiteBoard));
    }

    @Test
    void itShouldChangeToCircleState(){
        whiteBoard.getState().addCircle();
        Assertions.assertInstanceOf(AddCircle.class, whiteBoard.getState());
    }

    @Test
    void itShouldChangeToAddSquareState(){
        whiteBoard.getState().addSquare();
        Assertions.assertInstanceOf(AddSquare.class, whiteBoard.getState());
    }

    @Test
    void itShouldChangeToAddTriangleState(){
        whiteBoard.getState().addTriangle();
        Assertions.assertInstanceOf(AddTriangle.class, whiteBoard.getState());
    }

    @Test
    void itShouldChangeToMoveModeState(){
        whiteBoard.getState().moveMode();
        Assertions.assertInstanceOf(MoveMode.class, whiteBoard.getState());
    }

    @Test
    void itShouldChangeToSelectModeState(){
        whiteBoard.getState().selectMode();
        Assertions.assertInstanceOf(SelectMode.class, whiteBoard.getState());
    }
}

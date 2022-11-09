package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.*;

public abstract class WhiteBoardStateImpl implements WhiteBoardState {
    protected WhiteBoard whiteBoard;

    public WhiteBoardStateImpl(WhiteBoard whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    //-----------------------------------------//
    // Method of the interface WhiteBoardState //
    //-----------------------------------------//
    @Override
    public void addCircle() {
        whiteBoard.setState(AddCircle.getInstance(whiteBoard));
    }

    @Override
    public void addSquare() {
        whiteBoard.setState(AddSquare.getInstance(whiteBoard));
    }

    @Override
    public void addTriangle() {
        whiteBoard.setState(AddTriangle.getInstance(whiteBoard));
    }

    @Override
    public void selectMode() {
        whiteBoard.setState(SelectMode.getInstance(whiteBoard));
        whiteBoard.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void moveMode() {
        whiteBoard.setState(MoveMode.getInstance(whiteBoard));
        whiteBoard.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void resizeMode() {

    }

    @Override
    public void deleteMode() {

    }

    @Override
    public void groupMode() {

    }

    @Override
    public void ungroupMode() {

    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }
}

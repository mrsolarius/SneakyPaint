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
        whiteBoard.setState(new AddCircle(whiteBoard));
        whiteBoard.unSelectAll();
    }

    @Override
    public void addSquare() {
        whiteBoard.setState(new AddSquare(whiteBoard));
        whiteBoard.unSelectAll();
    }

    @Override
    public void addTriangle() {
        whiteBoard.setState(new AddTriangle(whiteBoard));
        whiteBoard.unSelectAll();
    }

    @Override
    public void selectMode() {
        whiteBoard.setState(new SelectMode(whiteBoard));
        whiteBoard.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void moveMode() {
        whiteBoard.setState(new MoveMode(whiteBoard));
        whiteBoard.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void loaded() {
        whiteBoard.setState(new SelectMode(whiteBoard));
        whiteBoard.loaded();
    }
}

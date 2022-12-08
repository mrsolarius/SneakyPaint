package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected.UnselectAllCommand;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class WhiteBoardStateImpl implements WhiteBoardState {
    protected WhiteBoard whiteBoard;

    protected WhiteBoardStateImpl(WhiteBoard whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    //-----------------------------------------//
    // Method of the interface WhiteBoardState //
    //-----------------------------------------//
    @Override
    public void addCircle() {
        whiteBoard.setState(new AddCircle(whiteBoard));
        new UnselectAllCommand(whiteBoard).execute();
    }

    @Override
    public void addSquare() {
        whiteBoard.setState(new AddSquare(whiteBoard));
        new UnselectAllCommand(whiteBoard).execute();
    }

    @Override
    public void addTriangle() {
        whiteBoard.setState(new AddTriangle(whiteBoard));
        new UnselectAllCommand(whiteBoard).execute();
    }

    @Override
    public void addImage(BufferedImage image) {
        whiteBoard.setState(new AddImage(whiteBoard, image));
        new UnselectAllCommand(whiteBoard).execute();
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
}

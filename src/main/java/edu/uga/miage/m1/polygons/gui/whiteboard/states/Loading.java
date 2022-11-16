package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.event.MouseEvent;

public class Loading extends WhiteBoardStateImpl{
    public Loading(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    @Override
    public void addCircle() {
        // no need to do anything here
    }

    @Override
    public void addSquare() {
        // no need to do anything here
    }

    @Override
    public void addTriangle() {
        // no need to do anything here
    }

    @Override
    public void selectMode() {
        // no need to do anything here
    }

    @Override
    public void moveMode() {
        // no need to do anything here
    }

    @Override
    public void loaded() {
        whiteBoard.setState(new AddSquare(whiteBoard));
        whiteBoard.loaded();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // no need to do anything here
    }
}

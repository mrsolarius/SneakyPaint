package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.event.MouseEvent;

public class Loading extends WhiteBoardStateImpl{
    public Loading(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    @Override
    public void addCircle() {

    }

    @Override
    public void addSquare() {
    }

    @Override
    public void addTriangle() {
    }

    @Override
    public void selectMode() {
    }

    @Override
    public void moveMode() {
    }

    @Override
    public void loaded() {
        whiteBoard.setState(new AddSquare(whiteBoard));
        whiteBoard.loaded();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

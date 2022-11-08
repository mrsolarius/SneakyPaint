package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.event.MouseEvent;

public class AddSquare extends WhiteBoardStateImpl{
    private static AddSquare instance;
    public static WhiteBoardState getInstance(WhiteBoard whiteBoard) {
        if (instance == null) {
            instance = new AddSquare(whiteBoard);
        }
        return instance;
    }

    private AddSquare(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }


    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        this.whiteBoard.placeSquare(e.getX(), e.getY());
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


    //---------------------------------------------//
    // Method of the interface MouseMotionListener //
    //---------------------------------------------//
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}

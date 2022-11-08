package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.event.MouseEvent;

public class AddCircle extends WhiteBoardStateImpl{
    private static AddCircle instance;
    public static WhiteBoardState getInstance(WhiteBoard whiteBoard) {
        if (instance == null) {
            instance = new AddCircle(whiteBoard);
        }
        return instance;
    }

    private AddCircle(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }


    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        this.whiteBoard.placeCircle(e.getX(), e.getY());
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

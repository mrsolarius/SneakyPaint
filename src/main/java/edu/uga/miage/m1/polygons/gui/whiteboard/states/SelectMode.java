package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectMode extends WhiteBoardStateImpl{

    SelectMode(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        this.whiteBoard.selectShape(e.getX(), e.getY());
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
        if (this.whiteBoard.collidingChildren(e.getX(), e.getY())!=null) {
            if (this.whiteBoard.collidingChildren(e.getX(),e.getY()).isSelected())
                this.moveMode();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.whiteBoard.collidingChildren(e.getX(), e.getY())!=null) {
            this.whiteBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            this.whiteBoard.setCursor(Cursor.getDefaultCursor());
        }
    }

}

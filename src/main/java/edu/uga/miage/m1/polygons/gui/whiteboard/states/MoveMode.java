package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.event.MouseEvent;

public class MoveMode extends WhiteBoardStateImpl{
    private int lastXMoved;
    private int lastYMoved;

    // Move mode need to be reset each time we use it this is why we don't use a singleton
    public MoveMode(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }


    //-----------------------------------------//
    // Method of the interface WhiteBoardState //
    //-----------------------------------------//
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
          // no need to do anything here
    }

    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        this.whiteBoard.setState(new SelectMode(this.whiteBoard));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.whiteBoard.setState(new SelectMode(this.whiteBoard));
        this.whiteBoard.selectShape(e.getX(), e.getY());
        this.lastXMoved = e.getX();
        this.lastYMoved = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.whiteBoard.setState(new SelectMode(this.whiteBoard));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // no need to do anything here
    }


    //---------------------------------------------//
    // Method of the interface MouseMotionListener //
    //---------------------------------------------//
    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastXMoved == 0 && lastYMoved == 0) {
            lastXMoved = e.getX();
            lastYMoved = e.getY();
        }
        int x = e.getX();
        int y = e.getY();
        this.whiteBoard.dragObject(x - this.lastXMoved, y - this.lastYMoved);
        this.lastXMoved = x;
        this.lastYMoved = y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.lastXMoved = e.getX();
        this.lastYMoved = e.getY();
    }

}

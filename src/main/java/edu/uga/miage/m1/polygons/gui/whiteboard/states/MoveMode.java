package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected.MoveCommand;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.mouse.SelectCommand;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MoveMode extends WhiteBoardStateImpl{
    private int lastXMoved;
    private int lastYMoved;

    private boolean isDragging;
    private int mouseClickLocationX;
    private int mouseClickLocationY;

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
    public void addImage(BufferedImage image) {
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
        new SelectCommand(this.whiteBoard, e.getX(), e.getY()).execute();
        this.lastXMoved = e.getX();
        this.lastYMoved = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.isDragging = false;
        new MoveCommand(this.whiteBoard, this.mouseClickLocationX, this.mouseClickLocationY, e.getX(), e.getY(),true).execute();
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
        if (!this.isDragging) {
            this.isDragging = true;
            this.mouseClickLocationX = e.getX();
            this.mouseClickLocationY = e.getY();
        }
        if (lastXMoved == 0 && lastYMoved == 0) {
            lastXMoved = e.getX();
            lastYMoved = e.getY();
        }
        int x = e.getX();
        int y = e.getY();
        new MoveCommand(this.whiteBoard, this.lastXMoved, this.lastYMoved, e.getX(), e.getY(),false).execute();
        this.lastXMoved = x;
        this.lastYMoved = y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.lastXMoved = e.getX();
        this.lastYMoved = e.getY();
    }

}

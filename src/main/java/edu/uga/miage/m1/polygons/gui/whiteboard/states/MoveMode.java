package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.event.MouseEvent;

public class MoveMode extends WhiteBoardStateImpl{
    private int lastXMoved;
    private int lastYMoved;
    private static MoveMode instance;
    public static WhiteBoardState getInstance(WhiteBoard whiteBoard) {
        if (instance == null) {
            instance = new MoveMode(whiteBoard);
        }
        return instance;
    }

    private MoveMode(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }


    //-----------------------------------------//
    // Method of the interface WhiteBoardState //
    //-----------------------------------------//
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

    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        this.whiteBoard.setState(SelectMode.getInstance(this.whiteBoard));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("MoveMode.mousePressed");
        this.whiteBoard.setState(SelectMode.getInstance(this.whiteBoard));
        this.whiteBoard.selectShape(e.getX(), e.getY());
        this.lastXMoved = e.getX();
        this.lastYMoved = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.whiteBoard.setState(SelectMode.getInstance(this.whiteBoard));
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
        System.out.println("MoveMode.mouseDragged");
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

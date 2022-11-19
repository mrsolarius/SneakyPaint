package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.command.AddCircleCommand;

import java.awt.event.MouseEvent;

public class AddCircle extends WhiteBoardStateImpl{

    public AddCircle(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }


    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        new AddCircleCommand(this.whiteBoard, e.getX(), e.getY()).execute();
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


    //---------------------------------------------//
    // Method of the interface MouseMotionListener //
    //---------------------------------------------//
    @Override
    public void mouseDragged(MouseEvent e) {
        // no need to do anything here
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // no need to do anything here
    }

}

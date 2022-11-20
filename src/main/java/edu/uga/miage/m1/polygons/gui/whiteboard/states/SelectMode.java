package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.mouse.SelectCommand;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectMode extends SimpleClickState{

    public SelectMode(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        new SelectCommand(this.whiteBoard, e.getX(), e.getY()).execute();
    }

    //---------------------------------------------//
    // Method of the interface MouseMotionListener //
    //---------------------------------------------//
    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.whiteBoard.collidingChildren(e.getX(), e.getY())!=null && (this.whiteBoard.collidingChildren(e.getX(),e.getY()).isSelected())){
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

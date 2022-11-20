package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation.AddCircleCommand;

import java.awt.event.MouseEvent;

public class AddCircle extends SimpleClickState{

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
}

package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation.AddTriangleCommand;

import java.awt.event.MouseEvent;

public class AddTriangle extends SimpleClickState{

    public AddTriangle(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        new AddTriangleCommand(this.whiteBoard, e.getX(), e.getY()).execute();
    }
}

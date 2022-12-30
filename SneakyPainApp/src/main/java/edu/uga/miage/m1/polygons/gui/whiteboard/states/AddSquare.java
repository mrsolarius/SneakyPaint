package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation.AddSquareCommand;

import java.awt.event.MouseEvent;

public class AddSquare extends SimpleClickState{

    public AddSquare(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }


    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        new AddSquareCommand(this.whiteBoard, e.getX(), e.getY()).execute();
    }
}

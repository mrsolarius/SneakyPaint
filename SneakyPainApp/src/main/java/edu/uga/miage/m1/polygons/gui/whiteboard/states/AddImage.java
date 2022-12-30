package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation.AddImageCommand;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AddImage extends SimpleClickState{
    private final BufferedImage image;

    public AddImage(WhiteBoard whiteBoard, BufferedImage image) {
        super(whiteBoard);
        this.image = image;
    }


    //---------------------------------------//
    // Method of the interface MouseListener //
    //---------------------------------------//
    @Override
    public void mouseClicked(MouseEvent e) {
        new AddImageCommand(this.whiteBoard, e.getX(), e.getY(),image).execute();
    }
}

package edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation;

import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import java.awt.image.BufferedImage;

public class AddImageCommand extends AbstractAddShapeCommand{
    private final BufferedImage image;

    public AddImageCommand(WhiteBoard receiver, int x, int y, BufferedImage image) {
        super(receiver, x, y);
        this.image = image;
    }

    @Override
    protected SimpleShape createShape(int x, int y) {
        return ShapeFactory.createImage(x, y, image);
    }
}

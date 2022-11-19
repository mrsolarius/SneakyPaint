package edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation;

import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class AddCircleCommand extends AbstractAddShapeCommand {

    public AddCircleCommand(WhiteBoard receiver, int x, int y) {
        super(receiver, x, y);
    }

    @Override
    protected SimpleShape createShape(int x, int y) {
        return ShapeFactory.createCircle(x, y);
    }
}

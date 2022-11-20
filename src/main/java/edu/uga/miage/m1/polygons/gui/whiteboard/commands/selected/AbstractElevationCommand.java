package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public abstract class AbstractElevationCommand extends AbstractSelectedCommand{
    protected AbstractElevationCommand(WhiteBoard receiver) {
        super(receiver);
    }

    protected void editElevation(int elevation) {
        for (SimpleShape shape: selectedShapes) {
            shape.getState().editElevation(elevation);
        }
        this.receiver.sortShapes();
        this.receiver.repaintAll();
    }
}

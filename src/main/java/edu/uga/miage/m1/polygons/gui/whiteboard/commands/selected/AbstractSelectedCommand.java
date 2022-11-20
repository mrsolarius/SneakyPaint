package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.AbstractCommand;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSelectedCommand extends AbstractCommand {
    protected List<SimpleShape> selectedShapes;

    protected AbstractSelectedCommand(WhiteBoard receiver) {
        super(receiver);
        this.selectedShapes = new ArrayList<>();
    }

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.selectedShapes.addAll(this.receiver.getSelectedShapes());
    }
}

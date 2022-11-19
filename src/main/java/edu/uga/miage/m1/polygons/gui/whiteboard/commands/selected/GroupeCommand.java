package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.shapes.Group;
import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class GroupeCommand extends AbstractSelectedCommand {
    private final Group group;

    public GroupeCommand(WhiteBoard receiver) {
        super(receiver);
        this.group = ShapeFactory.createGroup();
    }

    private void groupeSelectedShapes() {
        if (selectedShapes.size() > 1) {
            for (SimpleShape shape : selectedShapes) {
                this.group.addShapeAndDraw(shape,receiver.get2DGraphics());
            }
            this.receiver.removeShapes(selectedShapes);
            this.receiver.addShape(this.group);
            this.group.getState().select();
            this.receiver.repaintAll();
        }
    }

    @Override
    public void execute() {
        super.execute();
        groupeSelectedShapes();
    }

    @Override
    public void undo() {
        this.receiver.removeShape(this.group);
        for (SimpleShape shape : this.group.getShapes()) {
            shape.getState().ungroup();
        }
        this.receiver.addShapes(this.group.getShapes());
        this.receiver.repaintAll();
    }

    @Override
    public void redo() {
        groupeSelectedShapes();
    }
}

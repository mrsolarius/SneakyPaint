package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.shapes.Group;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class UngroupCommand extends AbstractSelectedCommand {

    private Group group;
    public UngroupCommand(WhiteBoard receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.selectedShapes = this.receiver.getSelectedShapes();
        unGroupSelectedShapes(true);
    }

    private void unGroupSelectedShapes(boolean saveHistory) {
        if (selectedShapes.size() == 1) {
            SimpleShape shape = selectedShapes.get(0);
            if (shape instanceof Group) {
                if (saveHistory){
                    this.receiver.getHistory().addCommand(this);
                }
                this.group = (Group) shape;
                for (SimpleShape simpleShape : this.group.getShapes()) {
                    simpleShape.getState().ungroup();
                }
                this.receiver.removeShape(this.group);
                this.receiver.addShapes(this.group.getShapes());
            }
        }
    }

    @Override
    public void undo() {
        if (this.group != null) {
            this.receiver.removeShapes(this.group.getShapes());
            this.receiver.addShape(this.group);
            for (SimpleShape simpleShape : this.group.getShapes()) {
                simpleShape.getState().group();
            }
        }
    }

    @Override
    public void redo() {
        unGroupSelectedShapes(false);
    }
}

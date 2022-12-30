package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.libs.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class UnselectAllCommand extends AbstractSelectedCommand {
    public UnselectAllCommand(WhiteBoard receiver) {
        super(receiver);
    }

    private void unSelectAll() {
        for (SimpleShape shape: this.selectedShapes) {
            shape.getState().unselect();
        }
        this.receiver.repaintAll();
    }

    @Override
    public void execute() {
        super.execute();
        unSelectAll();
    }

    @Override
    public void undo() {
        for (SimpleShape shape : this.selectedShapes) {
            shape.getState().select();
        }
        this.receiver.repaintAll();
    }

    @Override
    public void redo() {
        unSelectAll();
    }
}

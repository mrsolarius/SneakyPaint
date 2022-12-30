package edu.uga.miage.m1.polygons.gui.whiteboard.commands.mouse;

import edu.uga.miage.m1.libs.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected.UnselectAllCommand;

import java.util.ArrayList;
import java.util.List;

public class SelectCommand extends AbstractMouseCommand {

    List<SimpleShape> selectedShapes;
    public SelectCommand(WhiteBoard receiver, int x, int y) {
        super(receiver, x, y);
        selectedShapes = new ArrayList<>();
    }

    private void selectShapes(boolean withHistory) {
        boolean found = false;
        for (SimpleShape shape : receiver.getShapes()) {
            if (shape.isInside(x, y)) {
                found = true;
                shape.getState().select();
                selectedShapes.add(shape);
            }
        }
        if (!found) {
            new UnselectAllCommand(receiver).execute();
        }else{
            if (withHistory) {
                receiver.getHistory().addCommand(this);
            }
            this.receiver.repaintAll();
        }
    }

    @Override
    public void execute() {
        selectShapes(true);
    }

    @Override
    public void undo() {
        for (SimpleShape shape : this.selectedShapes) {
            shape.getState().unselect();
        }
        this.receiver.repaintAll();
    }

    @Override
    public void redo() {
        selectShapes(false);
    }
}

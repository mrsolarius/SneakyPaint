package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class MoveCommand extends AbstractSelectedCommand {
    private final int x;
    private final int y;
    private final int lastXMoved;
    private final int lastYMoved;
    private final boolean saveToHistory;

    public MoveCommand(WhiteBoard whiteBoard, int lastXMoved, int lastYMoved, int x, int y, boolean saveToHistory) {
        super(whiteBoard);
        this.x = x;
        this.y = y;
        this.lastXMoved = lastXMoved;
        this.lastYMoved = lastYMoved;
        this.saveToHistory = saveToHistory;
    }

    @Override
    public void execute() {
        this.selectedShapes = this.receiver.getSelectedShapes();
        if (saveToHistory) {
            this.receiver.getHistory().addCommand(this);
            return;
        }
        for (SimpleShape shape : this.selectedShapes) {
            shape.getState().move(x - this.lastXMoved, y - this.lastYMoved);
        }
        this.receiver.repaintAll();
    }

    @Override
    public void undo() {
        for (SimpleShape shape : this.selectedShapes) {
            shape.getState().move(this.lastXMoved - x, this.lastYMoved - y);
        }
        this.receiver.repaintAll();
    }

    @Override
    public void redo() {
        for (SimpleShape shape : this.selectedShapes) {
            shape.getState().move(x - this.lastXMoved, y - this.lastYMoved);
        }
        this.receiver.repaintAll();
    }

}

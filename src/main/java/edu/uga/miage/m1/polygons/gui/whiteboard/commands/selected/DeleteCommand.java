package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class DeleteCommand extends AbstractSelectedCommand {
    public DeleteCommand(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    @Override
    public void execute() {
        super.execute();
        this.receiver.removeShapes(selectedShapes);
    }

    @Override
    public void undo() {
        this.receiver.addShapes(selectedShapes);
    }

    @Override
    public void redo() {
        this.receiver.removeShapes(selectedShapes);
    }
}

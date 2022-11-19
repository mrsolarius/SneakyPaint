package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class LowerCommand extends AbstractElevationCommand {
    public LowerCommand(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    @Override
    public void execute() {
        super.execute();
        editElevation(-1);
    }

    @Override
    public void undo() {
        editElevation(1);
    }

    @Override
    public void redo() {
        editElevation(-1);
    }
}

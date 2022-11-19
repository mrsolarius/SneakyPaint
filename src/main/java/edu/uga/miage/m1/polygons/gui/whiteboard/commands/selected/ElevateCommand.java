package edu.uga.miage.m1.polygons.gui.whiteboard.commands.selected;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class ElevateCommand extends AbstractElevationCommand {

    public ElevateCommand(WhiteBoard receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        super.execute();
        editElevation(1);
    }

    @Override
    public void undo() {
        editElevation(-1);
    }


    @Override
    public void redo() {
        editElevation(1);
    }
}

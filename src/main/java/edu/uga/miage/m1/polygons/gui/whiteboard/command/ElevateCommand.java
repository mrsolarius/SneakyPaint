package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class ElevateCommand extends AbstractCommand{

    public ElevateCommand(WhiteBoard receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.receiver.elevateSelectedShapes();
    }
}

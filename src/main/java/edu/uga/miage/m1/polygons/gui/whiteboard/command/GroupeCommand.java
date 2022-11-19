package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class GroupeCommand extends AbstractCommand{

    public GroupeCommand(WhiteBoard receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.receiver.groupSelectedShapes();
    }
}

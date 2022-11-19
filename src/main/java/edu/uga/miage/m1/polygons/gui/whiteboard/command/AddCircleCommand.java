package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class AddCircleCommand extends MouseEventCommand {

    public AddCircleCommand(WhiteBoard receiver, int x, int y) {
        super(receiver, x, y);
    }

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.receiver.placeCircle(x, y);
    }
}

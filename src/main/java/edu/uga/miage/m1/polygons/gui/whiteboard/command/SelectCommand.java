package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class SelectCommand extends MouseEventCommand{

    public SelectCommand(WhiteBoard receiver, int x, int y) {
        super(receiver, x, y);
    }

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.receiver.selectShape(x, y);
    }
}

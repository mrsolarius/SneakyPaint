package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class MoveCommand extends MouseEventCommand{
    private int lastXMoved;
    private int lastYMoved;

    public MoveCommand(WhiteBoard whiteBoard, int lastXMoved, int lastYMoved, int x, int y) {
        super(whiteBoard, x, y);
        this.lastXMoved = lastXMoved;
        this.lastYMoved = lastYMoved;
    }

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.receiver.dragObject(x - this.lastXMoved, y - this.lastYMoved);
    }
}

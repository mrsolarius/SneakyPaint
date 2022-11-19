package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public abstract class MouseEventCommand extends AbstractCommand{
    protected int x;
    protected int y;

    public MouseEventCommand(WhiteBoard receiver, int x, int y) {
        super(receiver);
        this.x = x;
        this.y = y;
    }
}

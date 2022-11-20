package edu.uga.miage.m1.polygons.gui.whiteboard.commands.mouse;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.AbstractCommand;

public abstract class MouseEventCommand extends AbstractCommand {
    protected int x;
    protected int y;

    protected MouseEventCommand(WhiteBoard receiver, int x, int y) {
        super(receiver);
        this.x = x;
        this.y = y;
    }
}

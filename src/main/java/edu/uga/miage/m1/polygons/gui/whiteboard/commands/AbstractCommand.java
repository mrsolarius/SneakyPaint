package edu.uga.miage.m1.polygons.gui.whiteboard.commands;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public abstract class AbstractCommand implements Command {
    protected WhiteBoard receiver;

    public AbstractCommand(WhiteBoard receiver) {
        this.receiver = receiver;
    }
}

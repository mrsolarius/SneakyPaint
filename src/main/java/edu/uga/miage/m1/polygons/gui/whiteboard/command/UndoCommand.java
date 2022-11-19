package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class UndoCommand extends AbstractCommand{

    public UndoCommand(WhiteBoard receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.undo();
    }
}

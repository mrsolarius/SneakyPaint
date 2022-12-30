package edu.uga.miage.m1.polygons.gui.whiteboard.commands.simple;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.AbstractCommand;

public class RedoCommand extends AbstractCommand {
    public RedoCommand(WhiteBoard receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.getHistory().redo();
        this.receiver.repaintAll();
    }

    @Override
    public void undo() {
        // Do not implement anything here
    }

    @Override
    public void redo() {
        // Do not implement anything here
    }
}

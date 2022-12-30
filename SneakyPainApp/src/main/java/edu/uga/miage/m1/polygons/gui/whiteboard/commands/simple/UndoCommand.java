package edu.uga.miage.m1.polygons.gui.whiteboard.commands.simple;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.AbstractCommand;

public class UndoCommand extends AbstractCommand {

    public UndoCommand(WhiteBoard receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.getHistory().undo();
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

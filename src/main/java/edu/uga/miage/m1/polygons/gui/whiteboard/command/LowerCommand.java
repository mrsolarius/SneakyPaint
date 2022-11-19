package edu.uga.miage.m1.polygons.gui.whiteboard.command;

import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

public class LowerCommand extends AbstractCommand {
    public LowerCommand(WhiteBoard whiteBoard) {
        super(whiteBoard);
    }

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.receiver.lowerSelectedShapes();
    }
}

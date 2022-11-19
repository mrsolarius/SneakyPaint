package edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.mouse.MouseEventCommand;

public abstract class AbstractAddShapeCommand extends MouseEventCommand {
    protected SimpleShape shape;

    public AbstractAddShapeCommand(WhiteBoard receiver, int x, int y) {
        super(receiver, x, y);
    }

    abstract protected SimpleShape createShape(int x, int y);

    @Override
    public void execute() {
        this.receiver.getHistory().addCommand(this);
        this.shape = createShape(x-25, y-25);
        this.receiver.addShape(shape);
    }

    @Override
    public void undo() {
        this.receiver.removeShape(this.shape);
    }

    @Override
    public void redo() {
        this.receiver.addShape(this.shape);
    }
}

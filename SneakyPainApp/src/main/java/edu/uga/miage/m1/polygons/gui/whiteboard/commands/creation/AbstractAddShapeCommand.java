package edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation;

import edu.uga.miage.m1.libs.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.mouse.AbstractMouseCommand;

public abstract class AbstractAddShapeCommand extends AbstractMouseCommand {
    protected SimpleShape shape;

    protected AbstractAddShapeCommand(WhiteBoard receiver, int x, int y) {
        super(receiver, x, y);
    }

    protected abstract SimpleShape createShape(int x, int y);

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

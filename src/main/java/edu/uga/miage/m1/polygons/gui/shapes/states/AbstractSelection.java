package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public abstract class AbstractSelection extends SimpleShapeStateImpl{
    protected AbstractSelection(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void move(int x, int y) {
        shape.move(x, y);
    }

    @Override
    public void resize(int x, int y) {
        shape.resize(x, y);
    }

    @Override
    public void editElevation(int elevation) {
        shape.editElevation(elevation);
    }
}

package edu.uga.miage.m1.libs.shapes.states;

import edu.uga.miage.m1.libs.shapes.AbstractShape;

public abstract class AbstractSelectedState extends SimpleShapeStateImpl{
    protected AbstractSelectedState(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void move(int x, int y) {
        shape.move(x, y);
    }

    @Override
    public void editElevation(int elevation) {
        shape.editElevation(elevation);
    }
}

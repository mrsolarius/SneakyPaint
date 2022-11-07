package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public abstract class SimpleShapeStateImpl implements  SimpleShapeState {
    protected AbstractShape shape;

    public SimpleShapeStateImpl(AbstractShape shape) {
        this.shape = shape;
    }
}

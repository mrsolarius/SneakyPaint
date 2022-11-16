package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public abstract class SimpleShapeStateImpl implements  SimpleShapeState {
    protected AbstractShape shape;

    protected SimpleShapeStateImpl(AbstractShape shape) {
        this.shape = shape;
    }
}

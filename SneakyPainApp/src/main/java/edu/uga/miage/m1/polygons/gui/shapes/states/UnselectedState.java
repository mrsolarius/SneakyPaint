package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public class UnselectedState extends SimpleShapeStateImpl {
    public UnselectedState(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void move(int x, int y) {
        // we don't want to move when we are unselected
    }

    @Override
    public void editElevation(int elevation) {
        // we don't want to edit elevation when we are unselected
    }
    @Override
    public void group() {
        // we don't want to group when we are unselected
    }

    @Override
    public void ungroup() {
        // we don't want to ungroup when we are unselected
    }

    @Override
    public void select() {
        shape.changeState(new SelectedState(shape));
        shape.select();
    }

    @Override
    public void unselect() {
        this.shape.unselect();
    }
}

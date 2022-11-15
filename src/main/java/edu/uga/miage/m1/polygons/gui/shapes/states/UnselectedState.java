package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public class UnselectedState extends SimpleShapeStateImpl {
    public UnselectedState(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void move(int x, int y) {
    }

    @Override
    public void resize(int x, int y) {
    }

    @Override
    public void editElevation(int elevation) {
    }
    @Override
    public void group() {
    }

    @Override
    public void ungroup() {
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

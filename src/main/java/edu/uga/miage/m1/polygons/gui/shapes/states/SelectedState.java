package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public class SelectedState extends SimpleShapeStateImpl{
    public SelectedState(AbstractShape shape) {
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

    @Override
    public void group() {
        shape.changeState(new GroupState(shape));
        shape.group();
    }

    @Override
    public void ungroup() {
        shape.changeState(new SelectedState(shape));
        shape.select();
    }

    @Override
    public void select() {
        this.shape.select();
    }

    @Override
    public void unselect() {
        shape.changeState(new UnselectedState(shape));
        this.shape.unselect();
    }
}

package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public class GroupState extends SimpleShapeStateImpl{
    public GroupState(AbstractShape shape) {
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
        shape.group();
    }

    @Override
    public void ungroup() {
        shape.changeState(new SelectedState(shape));
        shape.select();
    }

    @Override
    public void select() {
        // we don't want to be selected when we are in a group
    }

    @Override
    public void unselect() {
        // we don't want to be unselected when we are in a group
    }
}

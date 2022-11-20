package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public class SelectedState extends AbstractSelection{
    public SelectedState(AbstractShape shape) {
        super(shape);
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

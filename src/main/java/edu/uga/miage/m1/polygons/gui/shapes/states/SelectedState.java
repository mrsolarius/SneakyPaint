package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public class SelectedState extends SimpleShapeStateImpl{
    public SelectedState(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void move(int x, int y) {
        System.out.println("SelectedState.move");
        shape.move(x, y);
    }

    @Override
    public void resize(int x, int y) {
        shape.resize(x, y);
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

package edu.uga.miage.m1.polygons.gui.shapes.states;

import edu.uga.miage.m1.polygons.gui.shapes.AbstractShape;

public class UnselectedState extends SimpleShapeStateImpl {
    public UnselectedState(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void move(int x, int y) {
        System.out.println("UnselectedState.move");
        shape.changeState(new SelectedState(shape));
        shape.select();
        shape.move(x, y);
    }

    @Override
    public void resize(int x, int y) {
        shape.changeState(new SelectedState(shape));
        shape.resize(x, y);
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

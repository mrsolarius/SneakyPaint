package edu.uga.miage.m1.libs.shapes.states;

import edu.uga.miage.m1.libs.shapes.AbstractShape;

public class GroupState extends AbstractSelectedState {
    public GroupState(AbstractShape shape) {
        super(shape);
    }

    @Override
    public void group() {
        shape.group();
    }

    @Override
    public void ungroup() {
        shape.ungroup();
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

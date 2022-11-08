package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.shapes.states.UnselectedState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe extends AbstractShape{
    private ArrayList<AbstractShape> shapes;

    public Groupe(Graphics2D g2, int x, int y) {
        super(g2, x, y);
    }

    public void addShape(AbstractShape shape) {
        shapes.add(shape);
    }

    public void removeShape(AbstractShape shape) {
        shapes.remove(shape);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void draw() {
        for (AbstractShape shape : shapes) {
            shape.draw();
        }
    }

    public List<SimpleShape> getShapes() {
        return Collections.singletonList((SimpleShape) shapes);
    }
}

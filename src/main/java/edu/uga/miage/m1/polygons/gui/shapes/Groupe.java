package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe extends AbstractShape{
    private final ArrayList<AbstractShape> shapes;

    public Groupe(Graphics2D g2, int x, int y) {
        super(g2, x, y);
        this.shapes = new ArrayList<>();
    }

    private void calculateNewHeight(AbstractShape shape) {
        int newHeight = shape.getY() + shape.getHeight() - this.y;
        // If the new height is greater than the current height, we update the height
        if (newHeight > this.height) {
            this.height = newHeight;
        }
    }

    private void calculateNewWidth(AbstractShape shape) {
        int newWidth = shape.getX() + shape.getWidth() - this.x;
        if (newWidth > this.width) {
            this.width = newWidth;
        }
    }

    private void updateSize(AbstractShape shape) {
        calculateNewHeight(shape);
        calculateNewWidth(shape);
    }

    private void updateAllSize() {
        this.height = 0;
        this.width = 0;
        for (AbstractShape shape : shapes) {
            updateSize(shape);
        }
    }

    public void addShape(AbstractShape shape) {
        shapes.add(shape);
        updateSize(shape);
        Collections.sort(shapes);
        shape.draw();
    }

    public void removeShape(AbstractShape shape) {
        shapes.remove(shape);
        updateAllSize();
        Collections.sort(shapes);
    }

    public void clear() {
        shapes.clear();
        this.height = 0;
        this.width = 0;
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

    /**
     * Implements the visitor pattern for the shape.
     * @param visitor The visitor to use.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void selectShape(int x, int y) {
        boolean found = false;
        for (AbstractShape shape : shapes) {
            if (shape.isInside(x, y)) {
                found = true;
                shape.clickSelect();
            }
        }
        if (!found) {
            unSelectAllChildren();
        }
    }

    public void unSelectAllChildren() {
        for (AbstractShape shape : shapes) {
            shape.clickUnselect();
        }
    }

    public AbstractShape collidingChildren(int x, int y) {
        for (AbstractShape shape : shapes) {
            if (shape.isInside(x, y)) {
                return shape;
            }
        }
        return null;
    }

    public void dragObject(int x, int y) {
        for (AbstractShape shape : shapes) {
            if (shape.isSelected()) {
                shape.clickMove(x, y);
            }
        }
    }
}

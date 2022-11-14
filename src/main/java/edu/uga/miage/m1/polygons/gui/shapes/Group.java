package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group extends AbstractShape{
    private final ArrayList<SimpleShape> shapes;

    public Group(Graphics2D g2) {
        super(g2, 0, 0);
        this.shapes = new ArrayList<>();
    }

    private void calculateNewHeight(SimpleShape shape) {
        int newHeight = shape.getY() + shape.getHeight() - this.y;
        // If the new height is greater than the current height, we update the height
        if (newHeight > this.height) {
            this.height = newHeight;
        }
    }

    private void calculateNewWidth(SimpleShape shape) {
        int newWidth = shape.getX() + shape.getWidth() - this.x;
        if (newWidth > this.width) {
            this.width = newWidth;
        }
    }

    private void calculateX(SimpleShape shape) {
        if (shape.getX() > this.x) {
            this.x = shape.getX();
        }
    }

    private void calculateY(SimpleShape shape) {
        if (shape.getY() > this.y) {
            this.y = shape.getY();
        }
    }

    private void updateSize() {
        //@TODO: this code needs to be refactored
        int x=Integer.MAX_VALUE,y=Integer.MAX_VALUE,height = 0,width = 0;
        for (SimpleShape shape: shapes){
            if (shape.getX() < x) {
                x = shape.getX();
            }
            if (shape.getY() < y) {
                y = shape.getY();
            }
            if (Math.abs((shape.getX() + shape.getWidth()) - (width+x)) > width) {
                width += Math.abs((shape.getX() + shape.getWidth()) - (width+x));
            }
            if (Math.abs((shape.getY() + shape.getHeight()) - (height+y)) > height) {
                height += Math.abs((shape.getY() + shape.getHeight()) - (height+y));
            }
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private void updateAllSize() {
        this.x = 0;
        this.y = 0;
        this.height = 0;
        this.width = 0;
    }

    public void addShape(SimpleShape shape) {
        shape.getState().group();
        shapes.add(shape);
        Collections.sort(shapes);
        updateSize();
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
        for (SimpleShape shape : shapes) {
            shape.draw();
        }
        if (isSelected()) {
            drawSelection();
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

    @Override
    public void move(int x, int y) {
        for (SimpleShape shape : shapes) {
            shape.getState().move(x, y);
        }
        this.x += x;
        this.y += y;
    }
}

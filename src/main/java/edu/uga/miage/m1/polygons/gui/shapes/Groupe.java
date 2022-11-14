package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.shapes.states.GroupState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe extends AbstractShape{
    private final ArrayList<SimpleShape> shapes;

    public Groupe(Graphics2D g2, int x, int y) {
        super(g2, x, y);
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

    private void updateSize(SimpleShape shape) {
        calculateX(shape);
        calculateY(shape);
        calculateNewHeight(shape);
        calculateNewWidth(shape);
    }

    private void updateAllSize() {
        this.x = 0;
        this.y = 0;
        this.height = 0;
        this.width = 0;
        for (SimpleShape shape : shapes) {
            updateSize(shape);
        }
    }

    public void addShape(SimpleShape shape) {
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
        for (SimpleShape shape : shapes) {
            shape.draw();
        }
    }

    public void groupeSelectedShapes(){
        Groupe groupe = new Groupe(g2, x, y);
        for (SimpleShape shape : shapes) {
            AbstractShape abstractShape = (AbstractShape) shape;
            if (shape.isSelected()) {
                shapes.remove(shape);
                abstractShape.changeState(new GroupState(abstractShape));
                groupe.addShape(shape);
            }
        }
        if (groupe.shapes.size() > 0) {
            this.addShape(groupe);
            groupe.select();
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
        for (SimpleShape shape : shapes) {
            if (shape.isInside(x, y)) {
                found = true;
                ((AbstractShape)shape).getState().select();
            }
        }
        if (!found) {
            unSelectAllChildren();
        }
    }

    public void unSelectAllChildren() {
        for (SimpleShape shape : shapes) {
            ((AbstractShape)shape).getState().unselect();
        }
    }

    public SimpleShape collidingChildren(int x, int y) {
        for (SimpleShape shape : shapes) {
            if (shape.isInside(x, y)) {
                return shape;
            }
        }
        return null;
    }

    public void dragObject(int x, int y) {
        for (SimpleShape shape : shapes) {
            if (shape.isSelected()) {
                ((AbstractShape)shape).getState().move(x, y);
            }
        }
    }
}

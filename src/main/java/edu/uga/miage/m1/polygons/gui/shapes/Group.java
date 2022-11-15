package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group extends AbstractShape{
    private final ArrayList<SimpleShape> shapes;

    protected Group(Graphics2D g2) {
        super(g2, 0, 0);
        this.shapes = new ArrayList<>();
    }

    private void updateSize() {
        int tempX=shapes.get(0).getX(),
            tempY=shapes.get(0).getY(),
            tempHeight = shapes.get(0).getHeight(),
            tempWidth = shapes.get(0).getWidth();
        for (SimpleShape shape: shapes){
            if(shape.getX()<tempX){
                tempWidth+=tempX-shape.getX();
                tempX=shape.getX();
            }
            if(shape.getY()<tempY){
                tempHeight+=tempY-shape.getY();
                tempY=shape.getY();
            }
            if(shape.getX()+shape.getWidth()>tempX+tempWidth){
                tempWidth=shape.getX()+shape.getWidth()-tempX;
            }
            if(shape.getY()+shape.getHeight()>tempY+tempHeight){
                tempHeight=shape.getY()+shape.getHeight()-tempY;
            }
        }
        this.x = tempX;
        this.y = tempY;
        this.width = tempWidth;
        this.height = tempHeight;
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

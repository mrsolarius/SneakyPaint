package edu.uga.miage.m1.libs.shapes;

import edu.uga.miage.m1.libs.shapes.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group extends AbstractShape{
    private final ArrayList<SimpleShape> shapes;

    protected Group() {
        super(0, 0);
        this.shapes = new ArrayList<>();
    }

    private void updateSize() {
        int tempX=shapes.get(0).getX();
        int tempY=shapes.get(0).getY();
        int tempHeight = shapes.get(0).getHeight();
        int tempWidth = shapes.get(0).getWidth();
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

    public void addShape(SimpleShape shape) {
        shape.getState().group();
        shapes.add(shape);
        Collections.sort(shapes);
        updateSize();
    }
    public void addShapeAndDraw(SimpleShape shape, Graphics2D g2) {
        addShape(shape);
        shape.draw(g2);
    }

    @Override
    public void draw(Graphics2D g2) {
        for (SimpleShape shape : shapes) {
            shape.draw(g2);
        }
        if (isSelected()) {
            drawSelection(g2);
        }
    }

    public List<SimpleShape> getShapes() {
        return shapes;
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

package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.shapes.states.SimpleShapeState;
import edu.uga.miage.m1.polygons.gui.shapes.states.UnselectedState;

import java.awt.*;

public abstract class AbstractShape implements SimpleShape, SimpleShapeState, Visitable, Comparable<AbstractShape> {
    private static final int SELECTED_BORDER_WIDTH = 3;
    private static final int BORDER_PADDING = 5;
    private static int elevationCounter;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int elevation;
    protected SimpleShapeState state;
    protected boolean selected;

    protected Graphics2D g2;

    public AbstractShape(Graphics2D g2,int x, int y) {
        this.x = x;
        this.y = y;
        this.state = new UnselectedState(this);
        this.height = 50;
        this.width = 50;
        this.selected = false;
        this.g2 = g2;
        AbstractShape.elevationCounter++;
        this.elevation = AbstractShape.elevationCounter;
    }

    abstract void draw();

    void drawSelection() {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(SELECTED_BORDER_WIDTH));
        g2.drawRect(x - BORDER_PADDING, y - BORDER_PADDING, width + BORDER_PADDING * 2, height + BORDER_PADDING * 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSelected() {
        return selected;
    }
    public boolean isInside(int x, int y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public void changeState(SimpleShapeState state) {
        this.state = state;
    }

    @Override
    public void move(int x, int y) {
        System.out.println("Move x: " + x + " y: " + y);
        this.x += x;
        this.y += y;
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void select() {
        this.selected = true;
    }

    @Override
    public void unselect() {
        this.selected = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    @Override
    public int compareTo(AbstractShape o) {
        return Integer.compare(this.getElevation(), o.getElevation());
    }

    public SimpleShapeState getState() {
        return state;
    }
}
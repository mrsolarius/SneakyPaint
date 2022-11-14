package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.*;

public class ShapeFactory {
    private static ShapeFactory instance = null;
    public static ShapeFactory getInstance(Graphics2D g2) {
        if (instance == null) {
            instance = new ShapeFactory(g2);
        }
        return instance;
    }

    private ShapeFactory(Graphics2D g2) {
        this.g2 = g2;
    }

    private final Graphics2D g2;

    public Circle createCircle(int x, int y){
        return new Circle(g2,x, y);
    }

    public Square createSquare(int x, int y){
        return new Square(g2,x, y);
    }

    public Triangle createTriangle(int x, int y){
        return new Triangle(g2,x, y);
    }

    public Group createGroup(){
        return new Group(g2);
    }

}

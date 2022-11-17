package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.*;

public class ShapeFactory {

    public static Circle createCircle(Graphics2D g2, int x, int y){
        return new Circle(g2,x, y);
    }

    public static Square createSquare(Graphics2D g2, int x, int y){
        return new Square(g2,x, y);
    }

    public static Triangle createTriangle(Graphics2D g2, int x, int y){
        return new Triangle(g2,x, y);
    }

    public static Group createGroup(Graphics2D g2){
        return new Group(g2);
    }

}

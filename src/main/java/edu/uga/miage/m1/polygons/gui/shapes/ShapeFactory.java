package edu.uga.miage.m1.polygons.gui.shapes;

public class ShapeFactory {

    public static Circle createCircle(int x, int y){
        return new Circle(x, y);
    }

    public static Square createSquare(int x, int y){
        return new Square(x, y);
    }

    public static Triangle createTriangle(int x, int y){
        return new Triangle(x, y);
    }

    public static Group createGroup(){
        return new Group();
    }

}

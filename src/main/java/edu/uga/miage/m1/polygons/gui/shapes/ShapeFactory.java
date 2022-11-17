package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.dto.ShapeDTO;

import java.util.ArrayList;
import java.util.List;

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

    public static List<SimpleShape> createShapesFromDTOs(List<ShapeDTO> shapesDTO){
        List<SimpleShape> shapes = new ArrayList<>();
        for (ShapeDTO shapeDTO: shapesDTO){
            shapes.add(createShapeFromDTO(shapeDTO));
        }
        return shapes;
    }
    public static SimpleShape createShapeFromDTO(ShapeDTO dto){
        switch (dto.getType()) {
            case "circle" -> {
                return new Circle(dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(),dto.getElevation());
            }
            case "square" -> {
                return new Square(dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(),dto.getElevation());
            }
            case "triangle" -> {
                return new Triangle(dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(),dto.getElevation());
            }
            case "group" -> {
                Group g = new Group();
                for (ShapeDTO child : dto.getChildren()) {
                    SimpleShape shape = createShapeFromDTO(child);
                    //need to set the shape to selected to be able to group it properly
                    shape.getState().select();
                    g.addShape(shape);
                }
                return g;
            }
            default -> throw new IllegalArgumentException("Unknown type: " + dto.getType());
        }
    }
}

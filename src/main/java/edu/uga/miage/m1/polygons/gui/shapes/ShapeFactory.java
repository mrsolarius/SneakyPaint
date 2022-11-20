package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.dto.ShapeDTO;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {

    private ShapeFactory() {}

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
        return switch (dto.getType()) {
            case "circle" -> new Circle(dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(),dto.getElevation());
            case "square" -> new Square(dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(),dto.getElevation());
            case "triangle" -> new Triangle(dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(),dto.getElevation());
            case "group" -> generateGroupFromDTO(dto);
            default -> throw new IllegalArgumentException("Unknown type: " + dto.getType());
        };
    }

    private static Group generateGroupFromDTO(ShapeDTO dto){
        Group g = new Group();
        for (ShapeDTO child : dto.getChildren()) {
            SimpleShape shape = createShapeFromDTO(child);
            //need to set the shape to selected to be able to group it properly
            shape.getState().select();
            g.addShape(shape);
        }
        return g;
    }
}

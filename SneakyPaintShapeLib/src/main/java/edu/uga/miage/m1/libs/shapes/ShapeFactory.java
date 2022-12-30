package edu.uga.miage.m1.libs.shapes;

import edu.uga.miage.m1.libs.shapes.dto.ShapeDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Base64;
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

    public static ImageShape createImage(int x, int y, BufferedImage image) {
        return new ImageShape(x, y, image);
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
            case "image" -> new ImageShape(dto.getX(), dto.getY(), dto.getWidth(), dto.getHeight(),dto.getElevation(), convertBase64ToBufferedImage(dto.getImage()));
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

    private static BufferedImage convertBase64ToBufferedImage(String base64Image) {
        byte[] bytes =  Base64.getDecoder().decode(base64Image);
        try {
            return ImageIO.read(new java.io.ByteArrayInputStream(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

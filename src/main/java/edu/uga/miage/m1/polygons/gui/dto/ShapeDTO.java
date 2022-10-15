package edu.uga.miage.m1.polygons.gui.dto;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import lombok.Getter;

@Getter
public class ShapeDTO {
    ShapeDTO() {}
    public ShapeDTO(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    private String type;

    private int x;

    private int y;

    public SimpleShape toEntity() {
        return switch (type) {
            case "circle" -> new Circle(x, y);
            case "square" -> new Square(x, y);
            case "triangle" -> new Triangle(x, y);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

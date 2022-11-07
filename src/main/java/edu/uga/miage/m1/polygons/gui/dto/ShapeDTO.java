package edu.uga.miage.m1.polygons.gui.dto;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.Getter;

import java.awt.*;

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

    public AbstractShape toEntity(Graphics2D g2) {
        return switch (type) {
            case "circle" -> new Circle(g2,x, y);
            case "square" -> new Square(g2,x, y);
            case "triangle" -> new Triangle(g2,x, y);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

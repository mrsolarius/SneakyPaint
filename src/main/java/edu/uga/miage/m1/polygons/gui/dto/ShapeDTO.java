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
        ShapeFactory shapeFactory = ShapeFactory.getInstance(g2);
        return switch (type) {
            case "circle" -> shapeFactory.createCircle(x, y);
            case "square" -> shapeFactory.createSquare(x, y);
            case "triangle" -> shapeFactory.createTriangle(x, y);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

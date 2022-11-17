package edu.uga.miage.m1.polygons.gui.dto;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.Getter;

import java.awt.*;
import java.util.List;

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
    private int height;
    private int width;
    private int elevation;
    private List<ShapesDTO> children;

    public AbstractShape toEntity(Graphics2D g2) {
        return switch (type) {
            case "circle" -> ShapeFactory.createCircle(g2,x, y);
            case "square" -> ShapeFactory.createSquare(g2,x, y);
            case "triangle" -> ShapeFactory.createTriangle(g2,x, y);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

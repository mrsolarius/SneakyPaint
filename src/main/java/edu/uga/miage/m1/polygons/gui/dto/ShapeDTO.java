package edu.uga.miage.m1.polygons.gui.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import lombok.Getter;

@Getter
public class ShapeDTO {
    @JsonIgnoreProperties
    @JsonProperty
    private String type;
    @JsonProperty
    private int x;
    @JsonProperty
    private int y;

    public SimpleShape toEntity() {
        switch (type) {
            case "circle":
                return new Circle(x, y);
            case "square":
                return new Square(x, y);
            case "triangle":
                return new Triangle(x, y);
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}

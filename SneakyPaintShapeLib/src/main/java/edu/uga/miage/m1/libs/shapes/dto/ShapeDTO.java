package edu.uga.miage.m1.libs.shapes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ShapeDTO {
    ShapeDTO() {}
    public ShapeDTO(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    @JsonProperty(value = "type", required = true)
    private String type;
    @JsonProperty(value = "x")
    private int x;
    @JsonProperty(value = "y")
    private int y;
    @JsonProperty(value = "height")
    private int height;
    @JsonProperty(value = "width")
    private int width;
    @JsonProperty(value = "elevation")
    private int elevation;
    @JsonProperty(value ="image")
    private String image;
    @JsonProperty(value = "children")
    private List<ShapeDTO> children;
}

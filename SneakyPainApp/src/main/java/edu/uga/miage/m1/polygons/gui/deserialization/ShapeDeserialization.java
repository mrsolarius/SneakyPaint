package edu.uga.miage.m1.polygons.gui.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.uga.miage.m1.libs.shapes.dto.ShapesDTO;

import java.io.UncheckedIOException;


public class ShapeDeserialization {

    private ShapeDeserialization() {}
    private static final ObjectMapper XML_MAPPER = new XmlMapper();
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static ShapesDTO deserialize(String shapes, Format format) {
        return format.equals(Format.JSON) ? deserialize(shapes, JSON_MAPPER) : deserialize(shapes, XML_MAPPER);
    }

    private static ShapesDTO deserialize(String shapes, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(shapes, ShapesDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new UncheckedIOException("Bad!", e);
        }
    }
}

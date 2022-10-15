package edu.uga.miage.m1.polygons.gui.deserialization;

import edu.uga.miage.m1.polygons.gui.dto.ShapesDTO;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestShapeDeserialization {

    @Test
    void testDeserializationJson() {
        String json = "{\"shapes\":[{\"type\":\"circle\",\"x\":10,\"y\":10},{\"type\":\"square\",\"x\":20,\"y\":20}]}";
        ShapesDTO shapesDTO = ShapeDeserialization.deserialize(json, Format.JSON);
        assert (shapesDTO != null);
        assert (shapesDTO.getShapes().size() == 2);
        assert (shapesDTO.getShapes().get(0).getType().equals("circle"));
        assert (shapesDTO.getShapes().get(0).getX() == 10);
        assert (shapesDTO.getShapes().get(0).getY() == 10);
        assert (shapesDTO.getShapes().get(1).getType().equals("square"));
        assert (shapesDTO.getShapes().get(1).getX() == 20);
        assert (shapesDTO.getShapes().get(1).getY() == 20);
    }

    @Test
    void testDeserializationXml(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><shapes><shape><type>circle</type><x>10</x><y>10</y></shape><shape><type>square</type><x>20</x><y>20</y></shape></shapes></root>";
        ShapesDTO shapesDTO = ShapeDeserialization.deserialize(xml, Format.XML);
        assert (shapesDTO != null);
        assert (shapesDTO.getShapes().size() == 2);
        assert (shapesDTO.getShapes().get(0).getType().equals("circle"));
        assert (shapesDTO.getShapes().get(0).getX() == 10);
        assert (shapesDTO.getShapes().get(0).getY() == 10);
        assert (shapesDTO.getShapes().get(1).getType().equals("square"));
        assert (shapesDTO.getShapes().get(1).getX() == 20);
        assert (shapesDTO.getShapes().get(1).getY() == 20);
    }

    @Test
    void testDeserializationJsonException(){
        String json = "Some random text";
        assertThrows(UncheckedIOException.class, () -> ShapeDeserialization.deserialize(json, Format.XML));
    }

    @Test
    void testDeserializationXmlException(){
        String xml = "Some random text";
        assertThrows(UncheckedIOException.class, () -> ShapeDeserialization.deserialize(xml, Format.XML));
    }
}

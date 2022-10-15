package edu.uga.miage.m1.polygons.gui.deserialization;

import edu.uga.miage.m1.polygons.gui.dto.ShapesDTO;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestShapeDeserialization {

    @Test
    void testDeserializationJson() {
        String json = "{\"shapes\":[{\"type\":\"circle\",\"x\":10,\"y\":10},{\"type\":\"square\",\"x\":20,\"y\":20}]}";
        ShapesDTO shapesDTO = ShapeDeserialization.deserialize(json, Format.JSON);
        assertNotNull (shapesDTO);
        assertEquals (shapesDTO.getShapes().size(),2);
        assertEquals (shapesDTO.getShapes().get(0).getType(),"circle");
        assertEquals (shapesDTO.getShapes().get(0).getX(),10);
        assertEquals (shapesDTO.getShapes().get(0).getY(),10);
        assertEquals (shapesDTO.getShapes().get(1).getType(),"square");
        assertEquals (shapesDTO.getShapes().get(1).getX(),20);
        assertEquals (shapesDTO.getShapes().get(1).getY(),20);
    }

    @Test
    void testDeserializationXml(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><shapes><shape><type>circle</type><x>10</x><y>10</y></shape><shape><type>square</type><x>20</x><y>20</y></shape></shapes></root>";
        ShapesDTO shapesDTO = ShapeDeserialization.deserialize(xml, Format.XML);
        assertNotNull (shapesDTO );
        assertEquals (shapesDTO.getShapes().size(),2);
        assertEquals (shapesDTO.getShapes().get(0).getType(),"circle");
        assertEquals (shapesDTO.getShapes().get(0).getX(),10);
        assertEquals (shapesDTO.getShapes().get(0).getY(),10);
        assertEquals (shapesDTO.getShapes().get(1).getType(),"square");
        assertEquals (shapesDTO.getShapes().get(1).getX(),20);
        assertEquals (shapesDTO.getShapes().get(1).getY(),20);
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

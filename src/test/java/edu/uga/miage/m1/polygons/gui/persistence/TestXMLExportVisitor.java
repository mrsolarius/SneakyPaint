package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestXMLExportVisitor {
    private final XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();

    @Test
    void testExport() {
        String xml = xmlExportVisitor.export(generateShapes().toArray(new SimpleShape[0]));
        System.out.println(xml);
        assertNotNull (xml );
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><shapes><shape><type>circle</type><x>10</x><y>10</y></shape><shape><type>square</type><x>20</x><y>20</y></shape><shape><type>triangle</type><x>30</x><y>30</y></shape></shapes></root>",xml);
    }

    private ArrayList<SimpleShape> generateShapes(){
        Circle c = ShapeFactory.createCircle(null,10, 10);
        Square s = ShapeFactory.createSquare(null,20, 20);
        Triangle t = ShapeFactory.createTriangle(null,30, 30);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(c);
        shapes.add(s);
        shapes.add(t);
        return shapes;
    }
}

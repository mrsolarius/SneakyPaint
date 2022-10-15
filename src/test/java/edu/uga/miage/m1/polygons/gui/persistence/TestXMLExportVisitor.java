package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestXMLExportVisitor {
    private final XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();

    @Test
    void testExport() {
        String xml = xmlExportVisitor.export(generateShapes().toArray(new SimpleShape[0]));
        assertNotNull (xml );
        assertEquals(xml,"<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><shapes><shape><type>circle</type><x>10</x><y>10</y></shape><shape><type>square</type><x>20</x><y>20</y></shape><shape><type>triangle</type><x>30</x><y>30</y></shape></shapes></root>");
    }

    private ArrayList<SimpleShape> generateShapes(){
        Circle c = new Circle(10, 10);
        Square s = new Square(20, 20);
        Triangle t = new Triangle(30, 30);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(c);
        shapes.add(s);
        shapes.add(t);
        return shapes;
    }
}

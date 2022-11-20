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
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ShapesDTO><shapes><shape><type>group</type><x>10</x><y>10</y><height>60</height><width>60</width><elevation>41</elevation><children><children><type>circle</type><x>10</x><y>10</y><height>50</height><width>50</width><elevation>38</elevation><children></children></children><children><type>square</type><x>20</x><y>20</y><height>50</height><width>50</width><elevation>39</elevation><children></children></children></children></shape><shape><type>triangle</type><x>30</x><y>30</y><height>50</height><width>50</width><elevation>40</elevation><children></children></shape></shapes></ShapesDTO>",xml);
    }

    private ArrayList<SimpleShape> generateShapes(){
        Circle c = ShapeFactory.createCircle(10, 10);
        Square s = ShapeFactory.createSquare(20, 20);
        Triangle t = ShapeFactory.createTriangle(30, 30);
        Group g = ShapeFactory.createGroup();
        g.addShape(c);
        g.addShape(s);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(g);
        shapes.add(t);
        return shapes;
    }
}

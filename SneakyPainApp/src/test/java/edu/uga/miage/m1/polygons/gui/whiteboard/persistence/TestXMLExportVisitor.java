package edu.uga.miage.m1.polygons.gui.whiteboard.persistence;

import edu.uga.miage.m1.libs.shapes.*;
import edu.uga.miage.m1.polygons.gui.persistence.XMLExportVisitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestXMLExportVisitor {
    private final XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();

    @Test
    void testExport() {
        String xml = xmlExportVisitor.export(generateShapes());
        assertNotNull (xml);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><ShapesDTO><shapes><shape><type>group</type><x>10</x><y>10</y><height>60</height><width>60</width><elevation>4</elevation><children><children><type>circle</type><x>10</x><y>10</y><height>50</height><width>50</width><elevation>2</elevation><children></children></children><children><type>square</type><x>20</x><y>20</y><height>50</height><width>50</width><elevation>3</elevation><children></children></children></children></shape><shape><type>triangle</type><x>30</x><y>30</y><height>50</height><width>50</width><elevation>4</elevation><children></children></shape></shapes></ShapesDTO>",xml);
    }

    private SimpleShape[] generateShapes(){
        Circle c = ShapeFactory.createCircle(10, 10);
        c.getState().select();
        c.getState().editElevation(-c.getElevation()+2);
        Square s = ShapeFactory.createSquare(20, 20);
        s.getState().select();
        s.getState().editElevation(-s.getElevation()+3);
        Triangle t = ShapeFactory.createTriangle(30, 30);
        t.getState().select();
        t.getState().editElevation(-t.getElevation()+4);
        Group g = ShapeFactory.createGroup();
        g.getState().select();
        g.getState().editElevation(-g.getElevation()+4);
        g.addShape(c);
        g.addShape(s);
        return new SimpleShape[]{g, t};
    }
}

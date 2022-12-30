package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestJSONExportVisitor {

    private final JSONExportVisitor jsonExportVisitor = new JSONExportVisitor();

    @Test
    void testExport() {
        String json = jsonExportVisitor.export(generateShapes().toArray(new SimpleShape[0]));
        System.out.println(json);
        assertNotNull(json);
        assertEquals("{\"shapes\":[{\"type\": \"group\",\"children\":[{\"type\":\"circle\",\"x\":10,\"y\":10,\"width\":50,\"height\":50,\"elevation\":2},{\"type\":\"square\",\"x\":20,\"y\":20,\"width\":50,\"height\":50,\"elevation\":3}]},{\"type\":\"triangle\",\"x\":30,\"y\":30,\"width\":50,\"height\":50,\"elevation\":4}]}",json);
    }

    private ArrayList<SimpleShape> generateShapes(){
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
        g.addShape(c);
        g.addShape(s);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(g);
        shapes.add(t);
        return shapes;
    }
}

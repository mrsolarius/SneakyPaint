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
        assertEquals("{\"shapes\":[{\"type\":\"circle\",\"x\":10,\"y\":10},{\"type\":\"square\",\"x\":20,\"y\":20},{\"type\":\"triangle\",\"x\":30,\"y\":30}]}",json);
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

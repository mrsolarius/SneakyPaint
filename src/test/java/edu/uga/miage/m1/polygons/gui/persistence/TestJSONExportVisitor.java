package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TestJSONExportVisitor {

    private final JSONExportVisitor jsonExportVisitor = new JSONExportVisitor();

    @Test
    void testExport() {
        String json = jsonExportVisitor.export(generateShapes().toArray(new SimpleShape[0]));
        assert (json != null);
        assert (json.equals("{\"shapes\":[{\"type\":\"circle\",\"x\":10,\"y\":10},{\"type\":\"square\",\"x\":20,\"y\":20},{\"type\":\"triangle\",\"x\":30,\"y\":30}]}"));
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

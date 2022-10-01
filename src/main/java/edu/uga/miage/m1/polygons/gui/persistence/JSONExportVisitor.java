package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSONExportVisitor implements Visitor {

    private String representation = null;

    public String export(SimpleShape ... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"shapes\":");
        sb.append("[");
        for (SimpleShape shape : args) {
            shape.accept(this);
            sb.append(this.getRepresentation());
            if (shape != args[args.length - 1]) {
                sb.append(",");
            }
            this.representation = null;
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void visit(Circle circle) {
        this.representation = generateBaseShapeJson(circle, "circle");
    }

    @Override
    public void visit(Square square) {
        this.representation = generateBaseShapeJson(square,"square");
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation = generateBaseShapeJson(triangle, "triangle");
    }

    public String generateBaseShapeJson(SimpleShape shape, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"shape\":");
        sb.append("{");
        sb.append("\"type\":\"").append(type).append("\",");
        sb.append("\"x\":").append(shape.getX()).append(",");
        sb.append("\"y\":").append(shape.getY());
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}

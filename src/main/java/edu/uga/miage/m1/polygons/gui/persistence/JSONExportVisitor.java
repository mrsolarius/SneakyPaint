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

    public JSONExportVisitor() {
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"shape\":");
        sb.append("{");
        sb.append("\"type\":\"circle\",");
        sb.append("\"x\":").append(circle.getX()).append(",");
        sb.append("\"y\":").append(circle.getY());
        sb.append("}");
        sb.append("}");
        this.representation = sb.toString();
    }

    @Override
    public void visit(Square square) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"shape\":");
        sb.append("{");
        sb.append("\"type\":\"square\",");
        sb.append("\"x\":").append(square.getX()).append(",");
        sb.append("\"y\":").append(square.getY());
        sb.append("}");
        sb.append("}");
        this.representation = sb.toString();
    }

    @Override
    public void visit(Triangle triangle) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"shape\":");
        sb.append("{");
        sb.append("\"type\":\"triangle\",");
        sb.append("\"x\":").append(triangle.getX()).append(",");
        sb.append("\"y\":").append(triangle.getY());
        sb.append("}");
        sb.append("}");
        this.representation = sb.toString();
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

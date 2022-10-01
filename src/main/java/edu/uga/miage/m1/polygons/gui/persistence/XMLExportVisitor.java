package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLExportVisitor implements Visitor {

    private String representation = null;

    public XMLExportVisitor() {
    }

    public String export(SimpleShape... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n");
        sb.append("<root>" + "\n");
        sb.append("<shapes>" + "\n");
        for (SimpleShape shape : args) {
            shape.accept(this);
            sb.append(this.getRepresentation()).append("\n");
            this.representation = null;
        }
        sb.append("</shapes>" + "\n");
        sb.append("</root>" + "\n");
        return sb.toString();
    }


    @Override
    public void visit(Circle circle) {
        StringBuilder sb = new StringBuilder();
        sb.append("<shape>");
        sb.append("<type>circle</type>");
        sb.append("<x>").append(circle.getX()).append("</x>");
        sb.append("<y>").append(circle.getY()).append("</y>");
        sb.append("</shape>");
        this.representation=sb.toString();
    }

    @Override
    public void visit(Square square) {
        StringBuilder sb = new StringBuilder();
        sb.append("<shape>");
        sb.append("<type>square</type>");
        sb.append("<x>").append(square.getX()).append("</x>");
        sb.append("<y>").append(square.getY()).append("</y>");
        sb.append("</shape>");
        this.representation=sb.toString();
    }

    @Override
    public void visit(Triangle triangle) {
        StringBuilder sb = new StringBuilder();
        sb.append("<shape>");
        sb.append("<type>triangle</type>");
        sb.append("<x>").append(triangle.getX()).append("</x>");
        sb.append("<y>").append(triangle.getY()).append("</y>");
        sb.append("</shape>");
        this.representation=sb.toString();
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}

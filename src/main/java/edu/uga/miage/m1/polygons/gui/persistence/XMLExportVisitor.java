package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLExportVisitor implements Visitor {

    private String representation = null;

    public String export(SimpleShape... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        sb.append("<root>");
        sb.append("<shapes>");
        for (SimpleShape shape : args) {
            shape.accept(this);
            sb.append(this.getRepresentation());
            this.representation = null;
        }
        sb.append("</shapes>");
        sb.append("</root>");
        return sb.toString();
    }


    @Override
    public void visit(Circle circle) {
        this.representation=generateBaseShapeXml(circle, "circle");
    }

    @Override
    public void visit(Square square) {
        this.representation=generateBaseShapeXml(square,"square");
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation=generateBaseShapeXml(triangle, "triangle");
    }

    @Override
    public void visit(Group group) {
        StringBuilder sb = new StringBuilder();
        sb.append("<groupe>");
        sb.append("<shapes>");
        for (SimpleShape shape : group.getShapes()) {
            shape.accept(this);
            sb.append(this.getRepresentation());
            this.representation = null;
        }
        sb.append("</shapes>");
        sb.append("</groupe>");
        this.representation = sb.toString();
    }

    public String generateBaseShapeXml(SimpleShape shape, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("<shape>");
        sb.append("<type>").append(type).append("</type>");
        sb.append("<x>").append(shape.getX()).append("</x>");
        sb.append("<y>").append(shape.getY()).append("</y>");
        sb.append("</shape>");
        return sb.toString();
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

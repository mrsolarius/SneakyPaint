package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLExportVisitor implements Visitor {

    private String representation = null;

    public String export(SimpleShape... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        sb.append("<ShapesDTO>");
        sb.append("<shapes>");
        for (SimpleShape shape : args) {
            shape.accept(this);
            sb.append(this.getRepresentation());
            this.representation = null;
        }
        sb.append("</shapes>");
        sb.append("</ShapesDTO>");
        return sb.toString();
    }


    @Override
    public void visit(Circle circle) {
        this.representation=generateBaseShapeXml(circle, "circle", Collections.emptyList());
    }

    @Override
    public void visit(Square square) {
        this.representation=generateBaseShapeXml(square,"square", Collections.emptyList());
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation=generateBaseShapeXml(triangle, "triangle", Collections.emptyList());
    }

    @Override
    public void visit(Group group) {
        this.representation=generateBaseShapeXml(group, "group", group.getShapes());
    }

    @Override
    public void visit(ImageShape imageShape) {
        this.representation=generateBaseShapeXml(imageShape, "image", Collections.emptyList());
        //remove the last </shape> tag
        this.representation=this.representation.substring(0, this.representation.length()-8);
        this.representation+="<image>"+imageShape.getImageBase64()+"</image>";
        this.representation+="</shape>";
    }

    private String generateBaseShapeXml(SimpleShape shape, String type, List<SimpleShape> children) {
        StringBuilder sb = new StringBuilder();
        sb.append("<shape>");
        sb.append("<type>").append(type).append("</type>");
        sb.append("<x>").append(shape.getX()).append("</x>");
        sb.append("<y>").append(shape.getY()).append("</y>");
        sb.append("<height>").append(shape.getHeight()).append("</height>");
        sb.append("<width>").append(shape.getWidth()).append("</width>");
        sb.append("<elevation>").append(shape.getElevation()).append("</elevation>");
        sb.append("<children>");
        for (SimpleShape child : children) {
            child.accept(this);
            sb.append(this.getRepresentation().replace("<shape>", "<children>").replace("</shape>", "</children>"));
            this.representation = null;
        }
        sb.append("</children>");
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

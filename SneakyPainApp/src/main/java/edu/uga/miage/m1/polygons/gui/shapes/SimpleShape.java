package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.shapes.states.SimpleShapeState;

import java.awt.*;

/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes. 
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public interface SimpleShape extends Visitable, Comparable<SimpleShape>
{
    int getX();
    int getY();

    int getWidth();
    int getHeight();
    int getElevation();
    SimpleShapeState getState();
    boolean isSelected();

    /**
     * Checks if the shape contains the given point.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return true if the shape contains the point, false otherwise
     */
    boolean isInside(int x, int y);

    /**
     * This method is used to paint the shape where ever you want.
     */
    void draw(Graphics2D g);

    /**
     * This method is used to compare elevation of two shapes.
     * it is used to sort shapes by elevation and draw them in the right order.
     * @param o the shape to compare with
     */
    int compareTo(SimpleShape o);
}
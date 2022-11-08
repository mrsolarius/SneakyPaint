package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;

/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes. 
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public interface SimpleShape extends Visitable
{
    int getX();
    int getY();
    boolean isInside(int x, int y);
}
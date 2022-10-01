package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;

import java.awt.Graphics2D;

/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes. 
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public interface SimpleShape extends Visitable
{

    /**
     * Method to draw the shape of the extension.
     * @param g2 The graphics object used for painting.
     **/
    void draw(Graphics2D g2);
    
    int getX();
    
    int getY();
}
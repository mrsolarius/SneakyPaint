package edu.uga.miage.m1.polygons.gui.whiteboard.states;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface WhiteBoardState extends MouseListener, MouseMotionListener {
    void addCircle();
    void addSquare();
    void addTriangle();
    void selectMode();
    void moveMode();
    void resizeMode();
    void deleteMode();
    void groupMode();
    void ungroupMode();
    void save();
    void loaded();
}

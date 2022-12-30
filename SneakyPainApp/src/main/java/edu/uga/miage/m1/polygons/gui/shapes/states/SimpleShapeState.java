package edu.uga.miage.m1.polygons.gui.shapes.states;

public interface SimpleShapeState {
    void move(int x, int y);
    void editElevation(int elevation);
    void group();
    void ungroup();
    void select();
    void unselect();
}

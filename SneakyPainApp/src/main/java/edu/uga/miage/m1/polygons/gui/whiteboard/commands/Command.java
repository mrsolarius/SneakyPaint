package edu.uga.miage.m1.polygons.gui.whiteboard.commands;

public interface Command {
    void execute();
    void undo();
    void redo();
}

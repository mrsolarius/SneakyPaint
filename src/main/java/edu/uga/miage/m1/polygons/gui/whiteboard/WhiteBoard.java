package edu.uga.miage.m1.polygons.gui.whiteboard;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.History;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.SelectMode;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.WhiteBoardState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class WhiteBoard extends JPanel {
    private final Logger logger = Logger.getLogger(WhiteBoard.class.getName());
    private static WhiteBoard instance;
    private final History history;
    public static WhiteBoard getInstance() {
        if (instance == null) {
            instance = new WhiteBoard();
        }
        return instance;
    }
    private WhiteBoardState state;
    private final ArrayList<SimpleShape> shapes;

    WhiteBoard(History history) {
        super();
        addMouseListener(this.state);
        addMouseMotionListener(this.state);
        shapes = new ArrayList<>();
        this.state = new SelectMode(this);
        this.history = history;
    }

    WhiteBoard() {
        this(new History());
    }

    public History getHistory() {
        return history;
    }

    public Graphics2D get2DGraphics() {
        return (Graphics2D) getGraphics();
    }

    public void setState(WhiteBoardState state) {
        removeMouseListener(this.state);
        removeMouseMotionListener(this.state);
        this.state = state;
        logger.log(new LogRecord(Level.INFO,"State changed to "+state.getClass().getSimpleName()));
        addMouseListener(this.state);
        addMouseMotionListener(this.state);
    }

    public void repaintAll() {
        if (get2DGraphics()!=null) {
            get2DGraphics().clearRect(0, 0, getWidth(), getHeight());
            for (SimpleShape shape : shapes) {
                shape.draw(get2DGraphics());
            }
        }
    }

    public void addShape(SimpleShape shape) {
        if (contains(shape.getX(), getY())) {
            shapes.add(shape);
            sortShapes();
            shape.draw(get2DGraphics());
        }
    }

    public void addShapes(List<SimpleShape> shapes) {
        for (SimpleShape shape : shapes) {
            addShape(shape);
        }
    }

    public void clearShapes() {
        shapes.clear();
        history.clear();
        repaintAll();
    }

    public SimpleShape collidingChildren(int x, int y) {
        for (SimpleShape shape : shapes) {
            if (shape.isInside(x, y)) {
                return shape;
            }
        }
        return null;
    }

    public WhiteBoardState getState() {
        return this.state;
    }

    public List<SimpleShape> getSelectedShapes() {
        return shapes.stream().filter(SimpleShape::isSelected).toList();
    }

    public void saveAsJson(){
        WhiteBoardExporter.saveAsJson(shapes);
    }

    public void saveAsXml(){
        WhiteBoardExporter.saveAsXml(shapes);
    }

    public void loadFile() {
        List<SimpleShape> loadedShapes = WhiteBoardLoader.load();
        if (!loadedShapes.isEmpty()) {
            clearShapes();
            addShapes(loadedShapes);
        }
    }

    public void undo() {
        history.undo();
        repaintAll();
    }

    public void removeShape(SimpleShape shape) {
        shapes.remove(shape);
        repaintAll();
    }

    public void removeShapes(List<SimpleShape> shapes) {
        this.shapes.removeAll(shapes);
        repaintAll();
    }

    public void sortShapes() {
        Collections.sort(shapes);
    }

    public void redo() {
        history.redo();
        repaintAll();
    }

    public List<SimpleShape> getShapes() {
        return shapes;
    }
}

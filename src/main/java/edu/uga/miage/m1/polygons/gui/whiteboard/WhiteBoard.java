package edu.uga.miage.m1.polygons.gui.whiteboard;

import edu.uga.miage.m1.polygons.gui.shapes.Group;
import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
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
    public static WhiteBoard getInstance() {
        if (instance == null) {
            instance = new WhiteBoard();
        }
        return instance;
    }
    private WhiteBoardState state;
    private final ArrayList<SimpleShape> shapes;

    private WhiteBoard() {
        super();
        addMouseListener(this.state);
        addMouseMotionListener(this.state);
        shapes = new ArrayList<>();
        this.state = new SelectMode(this);
    }

    private Graphics2D get2DGraphics() {
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

    private void addShape(SimpleShape shape) {
        if (canAddPlaceHere(shape.getX(), getY())) {
            shapes.add(shape);
            Collections.sort(shapes);
            shape.draw(get2DGraphics());
        }
    }

    private boolean canAddPlaceHere(int x, int y) {
        return contains(x, y);
    }

    public void clearShapes() {
        shapes.clear();
        repaintAll();
    }

    public void placeCircle(int x, int y) {
        addShape(ShapeFactory.createCircle(x, y));
    }
    public void placeSquare(int x, int y) {
        addShape(ShapeFactory.createSquare(x, y));
    }
    public void placeTriangle(int x, int y) {
        addShape(ShapeFactory.createTriangle(x, y));
    }

    public void selectShape(int x, int y) {
        boolean found = false;
        for (SimpleShape shape : shapes) {
            if (shape.isInside(x, y)) {
                found = true;
                shape.getState().select();
            }
        }
        if (!found) {
            unSelectAll();
        }
        repaintAll();
    }

    public void dragObject(int x, int y) {
        for (SimpleShape shape : shapes) {
            if (shape.isSelected()) {
                shape.getState().move(x, y);
            }
        }
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

    public void unSelectAll() {
        for (SimpleShape shape : shapes) {
            shape.getState().unselect();
        }
        repaintAll();
    }

    private List<SimpleShape> getSelectedShapes() {
        return shapes.stream().filter(SimpleShape::isSelected).toList();
    }

    public void groupSelectedShapes(){
        List<SimpleShape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() > 1) {
            Group group = ShapeFactory.createGroup();
            for (SimpleShape shape : selectedShapes) {
                group.addShape(shape,get2DGraphics());
            }
            shapes.removeAll(selectedShapes);
            shapes.add(group);
            group.getState().select();
            group.draw(get2DGraphics());
        }
        repaintAll();
    }

    public void ungroupSelectedShapes() {
        List<SimpleShape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() == 1) {
            for (SimpleShape shape : selectedShapes) {
                if (shape instanceof Group) {
                    List<SimpleShape>  children = ((Group) shape).getShapes();
                    for (SimpleShape simpleShape : children) {
                        simpleShape.getState().ungroup();
                    }
                    shapes.remove(shape);
                    shapes.addAll(((Group) shape).getShapes());
                }
                shape.getState().ungroup();
            }
        }
        repaintAll();
    }

    public void elevateSelectedShapes() {
        List<SimpleShape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() == 1) {
            for (SimpleShape shape : selectedShapes) {
                shape.getState().editElevation(1);
            }
            Collections.sort(shapes);
            repaintAll();
        }
    }

    public void lowerSelectedShapes() {
        List<SimpleShape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() == 1) {
            for (SimpleShape shape : selectedShapes) {
                shape.getState().editElevation(-1);
            }
            Collections.sort(shapes);
            repaintAll();
        }
    }

    public void deleteSelectedShapes() {
        List<SimpleShape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() > 0) {
            shapes.removeAll(selectedShapes);
            repaintAll();
        }
    }

    public void saveAsJson(){
        WhiteBoardExporter.saveAsJson(shapes);
    }

    public void saveAsXml(){
        WhiteBoardExporter.saveAsXml(shapes);
    }

    public void loadFile() {
        this.clearShapes();
        this.shapes.addAll(WhiteBoardLoader.load());
        repaintAll();
    }
}

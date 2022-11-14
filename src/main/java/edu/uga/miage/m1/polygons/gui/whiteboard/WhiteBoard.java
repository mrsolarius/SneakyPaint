package edu.uga.miage.m1.polygons.gui.whiteboard;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.SelectMode;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.WhiteBoardState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WhiteBoard extends JPanel {
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
        //setBackground(Color.WHITE);
        setLayout(null);
        setMinimumSize(new Dimension(400, 400));
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
        System.out.println("State changed to " + state.getClass().getSimpleName());
        addMouseListener(this.state);
        addMouseMotionListener(this.state);
    }

    private void repaintAll() {
        if (get2DGraphics()!=null) {
            get2DGraphics().clearRect(0, 0, getWidth(), getHeight());
            //get2DGraphics().dispose();
            for (SimpleShape shape : shapes) {
                shape.draw();
            }
        }
    }

    private void addShape(AbstractShape shape) {
        if (canAddPlaceHere(shape.getX(), getY())) {
            shapes.add(shape);
            Collections.sort(shapes);
            shape.draw();
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
        addShape(new Circle(get2DGraphics(), x, y));
    }
    public void placeSquare(int x, int y) {
        addShape(new Square(get2DGraphics(), x, y));
    }
    public void placeTriangle(int x, int y) {
        addShape(new Triangle(get2DGraphics(), x, y));
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
        return shapes.stream().filter(SimpleShape::isSelected).collect(Collectors.toList());
    }

    public void groupSelectedShapes(){
        List<SimpleShape> selectedShapes = getSelectedShapes();
        if (selectedShapes.size() > 1) {
            Group group = new Group(get2DGraphics());
            for (SimpleShape shape : selectedShapes) {
                group.addShape(shape);
            }
            shapes.removeAll(selectedShapes);
            shapes.add(group);
            group.getState().select();
            group.draw();
        }
        repaintAll();
    }
}

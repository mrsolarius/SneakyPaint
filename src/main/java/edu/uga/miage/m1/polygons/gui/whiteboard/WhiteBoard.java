package edu.uga.miage.m1.polygons.gui.whiteboard;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.SelectMode;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.WhiteBoardState;

import javax.swing.*;
import java.awt.*;

public class WhiteBoard extends JPanel {
    private static WhiteBoard instance;
    public static WhiteBoard getInstance() {
        if (instance == null) {
            instance = new WhiteBoard();
        }
        return instance;
    }
    private WhiteBoardState state;
    private final Groupe mainGroupe;

    private WhiteBoard() {
        super();
        //setBackground(Color.WHITE);
        setLayout(null);
        setMinimumSize(new Dimension(400, 400));
        addMouseListener(this.state);
        addMouseMotionListener(this.state);
        mainGroupe = new Groupe(get2DGraphics(), 0, 0);
        this.state = SelectMode.getInstance(this);
    }

    private Graphics2D get2DGraphics() {
        return (Graphics2D) getGraphics();
    }

    public void setState(WhiteBoardState state) {
        removeMouseListener(this.state);
        removeMouseMotionListener(this.state);
        this.state = state;
        addMouseListener(this.state);
        addMouseMotionListener(this.state);
    }

    public Groupe getMainGroupe() {
        return mainGroupe;
    }

    private void repaintAll() {
        get2DGraphics().clearRect(0, 0, getWidth(), getHeight());
        get2DGraphics().dispose();
        mainGroupe.draw();
    }

    private void addShape(AbstractShape shape) {
        if (canAddPlaceHere(shape.getX(), getY())) {
            mainGroupe.addShape(shape);
        }
    }

    private boolean canAddPlaceHere(int x, int y) {
        return contains(x, y);
    }

    public void clearShapes() {
        mainGroupe.clear();
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
        mainGroupe.selectShape(x, y);
        repaintAll();
    }

}

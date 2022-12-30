package edu.uga.miage.m1.polygons.gui.whiteboard;

import edu.uga.miage.m1.polygons.gui.shapes.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.History;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation.AddCircleCommand;
import edu.uga.miage.m1.polygons.gui.whiteboard.commands.creation.AddSquareCommand;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.MoveMode;
import edu.uga.miage.m1.polygons.gui.whiteboard.states.SelectMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class TestWhiteBoard {

    WhiteBoard whiteBoard;
    History history;

    @BeforeEach
    void setUp() {
        history = Mockito.mock(History.class);
        Frame frame = new JFrame();
        whiteBoard = new WhiteBoard(history);
        frame.add(whiteBoard);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);
    }

    @Test
    void itShouldBeInSelectModeWhenRetrievingTheWhiteBoard() {
        Assertions.assertInstanceOf(SelectMode.class, whiteBoard.getState());
    }

    @Test
    void itShouldChangeToSelectState(){
        WhiteBoard whiteBoard = WhiteBoard.getInstance();
        whiteBoard.setState(new MoveMode(whiteBoard));
        Assertions.assertInstanceOf(MoveMode.class, whiteBoard.getState());
    }

    @Test
    void itShouldCallDrawMethodeOnEachShape(){
        SimpleShape shape = Mockito.mock(SimpleShape.class);
        SimpleShape shape2 = Mockito.mock(SimpleShape.class);
        SimpleShape shape3 = Mockito.mock(SimpleShape.class);
        whiteBoard.addShape(shape);
        whiteBoard.addShape(shape2);
        whiteBoard.addShape(shape3);
        // assert that the draw method is called on each shape
        Mockito.verify(shape, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape2, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape3, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
    }

    @Test
    void itShouldAddShapes(){
        SimpleShape shape = Mockito.mock(SimpleShape.class);
        SimpleShape shape2 = Mockito.mock(SimpleShape.class);
        SimpleShape shape3 = Mockito.mock(SimpleShape.class);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(shape);
        shapes.add(shape2);
        shapes.add(shape3);
        whiteBoard.addShapes(shapes);
        Assertions.assertEquals(3, whiteBoard.getShapes().size());
        Mockito.verify(shape, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape2, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape3, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
    }

    @Test
    void itShouldRemoveShapes(){
        SimpleShape shape = Mockito.mock(SimpleShape.class);
        SimpleShape shape2 = Mockito.mock(SimpleShape.class);
        SimpleShape shape3 = Mockito.mock(SimpleShape.class);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(shape);
        shapes.add(shape2);
        shapes.add(shape3);
        whiteBoard.addShapes(shapes);
        whiteBoard.removeShape(shape);
        Assertions.assertEquals(2, whiteBoard.getShapes().size());
        Mockito.verify(shape, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape2, Mockito.times(2)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape3, Mockito.times(2)).draw(Mockito.any(Graphics2D.class));
    }

    @Test
    void itShouldRemoveAllShapes(){
        SimpleShape shape = Mockito.mock(SimpleShape.class);
        SimpleShape shape2 = Mockito.mock(SimpleShape.class);
        SimpleShape shape3 = Mockito.mock(SimpleShape.class);
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        shapes.add(shape);
        shapes.add(shape2);
        shapes.add(shape3);
        whiteBoard.addShapes(shapes);
        Assertions.assertEquals(3, whiteBoard.getShapes().size());
        whiteBoard.removeShapes(shapes);
        Assertions.assertEquals(0, whiteBoard.getShapes().size());
        Mockito.verify(shape, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape2, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
        Mockito.verify(shape3, Mockito.times(1)).draw(Mockito.any(Graphics2D.class));
    }

    @Test
    void itShouldSortShapesByElevationWhenWeAddIt(){
        SimpleShape shape = ShapeFactory.createCircle(0, 0);
        SimpleShape shape2 = ShapeFactory.createSquare(25, 25);
        SimpleShape shape3 = ShapeFactory.createTriangle(50, 50);



        shape.getState().select();
        shape.getState().editElevation(-shape.getElevation());
        shape.getState().editElevation(3);
        shape.getState().unselect();

        shape2.getState().select();
        shape2.getState().editElevation(-shape2.getElevation());
        shape2.getState().editElevation(1);
        shape2.getState().unselect();

        shape3.getState().select();
        shape3.getState().editElevation(-shape3.getElevation());
        shape3.getState().editElevation(2);
        shape3.getState().unselect();

        whiteBoard.addShape(shape);
        Assertions.assertEquals(shape, whiteBoard.getShapes().get(0));
        whiteBoard.addShape(shape2);
        Assertions.assertEquals(shape2, whiteBoard.getShapes().get(0));
        Assertions.assertEquals(shape, whiteBoard.getShapes().get(1));
        whiteBoard.addShape(shape3);
        Assertions.assertEquals(shape3, whiteBoard.getShapes().get(1));
        Assertions.assertEquals(shape2, whiteBoard.getShapes().get(0));
        Assertions.assertEquals(shape, whiteBoard.getShapes().get(2));
    }

    @Test
    void itShouldRemoveShape(){
        SimpleShape shape = Mockito.mock(SimpleShape.class);
        whiteBoard.addShape(shape);
        whiteBoard.removeShape(shape);
        Assertions.assertEquals(0, whiteBoard.getShapes().size());
    }

    @Test
    void itShouldClearAllShapes(){
        SimpleShape shape = Mockito.mock(SimpleShape.class);
        SimpleShape shape2 = Mockito.mock(SimpleShape.class);
        SimpleShape shape3 = Mockito.mock(SimpleShape.class);
        whiteBoard.addShape(shape);
        whiteBoard.addShape(shape2);
        whiteBoard.addShape(shape3);
        whiteBoard.clearShapes();
        Assertions.assertEquals(0, whiteBoard.getShapes().size());
    }

    @Test
    void itShouldReturnTheShapeAtTheGivenPosition(){
        SimpleShape shape = ShapeFactory.createCircle(0,0);
        SimpleShape shape2 = ShapeFactory.createCircle(50,0);
        SimpleShape shape3 = ShapeFactory.createCircle(100,0);
        whiteBoard.addShape(shape);
        whiteBoard.addShape(shape2);
        whiteBoard.addShape(shape3);
        Assertions.assertEquals(shape, whiteBoard.collidingChildren(25, 25));
        Assertions.assertEquals(shape2, whiteBoard.collidingChildren(75, 25));
        Assertions.assertEquals(shape3, whiteBoard.collidingChildren(125, 25));
    }

    @Test
    void itShouldNotReturnShapeAtGivenPosition(){
        SimpleShape shape = ShapeFactory.createCircle(0,0);
        SimpleShape shape2 = ShapeFactory.createCircle(50,0);
        SimpleShape shape3 = ShapeFactory.createCircle(100,0);
        whiteBoard.addShape(shape);
        whiteBoard.addShape(shape2);
        whiteBoard.addShape(shape3);
        Assertions.assertNull(whiteBoard.collidingChildren(25, 75));
        Assertions.assertNull(whiteBoard.collidingChildren(75, 75));
        Assertions.assertNull(whiteBoard.collidingChildren(125, 75));
    }

    @Test
    void itShouldCallUndoOnHistoryAndRepaintAll(){
        whiteBoard.getState().addCircle();
        new AddCircleCommand(whiteBoard, 125, 110).execute();
        whiteBoard.getState().addSquare();
        new AddSquareCommand(whiteBoard, 40, 60).execute();
        whiteBoard.undo();
        Mockito.verify(history, Mockito.times(1)).undo();
    }

    @Test
    void itShouldCallRedoOnHistoryAndRepaintAll(){
        whiteBoard.getState().addCircle();
        new AddCircleCommand(whiteBoard, 30, 45).execute();
        whiteBoard.getState().addSquare();
        new AddSquareCommand(whiteBoard, 60, 75).execute();
        whiteBoard.undo();
        whiteBoard.redo();
        Mockito.verify(history, Mockito.times(1)).redo();
    }

    @Test
    void itShouldGetInstanceOfWhiteboard(){
        WhiteBoard whiteBoard = WhiteBoard.getInstance();
        Assertions.assertInstanceOf(WhiteBoard.class, whiteBoard);
        Assertions.assertNotNull(whiteBoard);
    }

}

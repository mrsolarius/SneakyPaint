package edu.uga.miage.m1.libs.shapes;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static edu.uga.miage.m1.libs.shapes.AbstractShape.SELECTED_BORDER_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

class TestCircle {
    @Test
    void testCircle() {
        Circle c = ShapeFactory.createCircle(10, 10);
        assertEquals(10,c.getX());
        assertEquals( 10,c.getY());
    }

    @Test
    void shouldDrawCircle() {
        Circle c = ShapeFactory.createCircle(10, 10);
        //Mockito mock the Graphics2D object
        Graphics2D graphics2D = Mockito.mock(Graphics2D.class);
        c.draw(graphics2D);
        //Verify that the drawOval method is called
        Mockito.verify(graphics2D).setPaint(argThat(x->{
            assertInstanceOf(GradientPaint.class,x);
            GradientPaint gp = (GradientPaint) x;
            assertEquals(10,gp.getPoint1().getX());
            assertEquals(10,gp.getPoint1().getY());
            assertEquals(c.getX() + 50,gp.getPoint2().getX());
            assertEquals(c.getY(),gp.getPoint2().getY());
            assertEquals(Color.RED,gp.getColor1());
            assertEquals(Color.WHITE,gp.getColor2());
            return true;
        }));
        Mockito.verify(graphics2D).fill(argThat(x->{
            assertInstanceOf(Ellipse2D.Double.class,x);
            Ellipse2D.Double ellipse = (Ellipse2D.Double) x;
            assertEquals(c.getX(),ellipse.getX());
            assertEquals(c.getY(),ellipse.getY());
            assertEquals(c.getWidth(),ellipse.getWidth());
            assertEquals(c.getHeight(),ellipse.getHeight());
            return true;
        }));
        Mockito.verify(graphics2D).setColor(Color.black);
        Mockito.verify(graphics2D).setStroke(argThat(x->{
            assertInstanceOf(BasicStroke.class,x);
            BasicStroke bs = (BasicStroke) x;
            assertEquals(2.0f,bs.getLineWidth());
            return true;
        }));
        Mockito.verify(graphics2D).draw(any(Ellipse2D.Double.class));
    }

    @Test
    void shouldDrawSelectionSquare() {
        Circle c = ShapeFactory.createCircle(10, 10);
        c.getState().select();
        //Mockito mock the Graphics2D object
        Graphics2D graphics2D = Mockito.mock(Graphics2D.class);
        Graphics2D graphics2D1 = Mockito.mock(Graphics2D.class);
        c.draw(graphics2D1);
        c.drawSelection(graphics2D);
        //Verify that the drawOval method is called
        Mockito.verify(graphics2D).setColor(argThat(x->{
            assertEquals(Color.black,x);
            return true;
        }));
        Mockito.verify(graphics2D).setStroke(argThat(x->{
            assertInstanceOf(BasicStroke.class,x);
            BasicStroke bs = (BasicStroke) x;
            assertEquals(SELECTED_BORDER_WIDTH,bs.getLineWidth());
            return true;
        }));
    }
}

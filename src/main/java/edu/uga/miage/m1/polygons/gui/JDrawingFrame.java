package edu.uga.miage.m1.polygons.gui;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.Serial;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.*;

import edu.uga.miage.m1.polygons.gui.persistence.JSONExportVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLExportVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class JDrawingFrame extends JFrame
    implements MouseListener, MouseMotionListener
{
    private static final Logger logger = Logger.getLogger(JDrawingFrame.class.getName());
	private enum Shapes {SQUARE, TRIANGLE, CIRCLE}
    @Serial
    private static final long serialVersionUID = 1L;
    private final JToolBar toolbar;
    private Shapes selected;
    private final JPanel panel;
    private final JLabel label;
    private final transient ActionListener reusableActionListener = new ShapeActionListener();

    private final List<SimpleShape> shapes = new ArrayList<>();
    
    /**
     * Tracks buttons to manage the background.
     */
    private final Map<Shapes, JButton> buttons = new EnumMap<>(Shapes.class);

    /**
     * Default constructor that populates the main window.
     * @param frameName The name of the frame.
     */
    public JDrawingFrame(String frameName)
    {
        super(frameName);
        // Instantiates components
        toolbar = new JToolBar("Toolbar");
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setMinimumSize(new Dimension(400, 400));
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        label = new JLabel(" ", SwingConstants.LEFT);
        
        // Fills the panel
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        
        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(Shapes.TRIANGLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(Shapes.CIRCLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));

        // add export button in the menu
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener((ActionEvent actionEvent)->this.exportToFileShapes(this.generateExportMenu()));
        toolbar.add(exportButton);


        setPreferredSize(new Dimension(400, 400));
    }


    /**
     * Generates popuo menu to select export format
     * @return selected format {JSON,XML,NONE}
     */
    private String generateExportMenu(){
        //popup a dialog to ask if we export in json or xml
        String[] choices = { "JSON", "XML" };
        return (String) JOptionPane.showInputDialog(
                null,
                "Export to ...",
                "Export",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices, // Array of choices
                choices[0] // Initial choice
        );
    }

    private void exportToFileShapes(String type){
        switch (type){
            case "JSON" -> {
                String json = new JSONExportVisitor().export(shapes.toArray(new SimpleShape[0]));
                this.saveFile(json,"json");
            }
            case "XML" -> {
                String xml = new XMLExportVisitor().export(shapes.toArray(new SimpleShape[0]));
                this.saveFile(xml,"xml");
            }
            default -> logger.log(new LogRecord(Level.WARNING,"No export format selected"));
        }
    }

    /**
     * save file with content and extention
     */
    private void saveFile(String content, String extention){
        try {
            File file = new File("shapes."+extention);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (Exception e) {
            logger.log(new LogRecord(Level.SEVERE, "Error while saving file"));
        }
    }




    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
    **/
    private void addShape(Shapes shape, ImageIcon icon)
    {
        JButton button = new JButton(icon);
		button.setBorderPainted(false);
        buttons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(reusableActionListener);

        if (selected == null)
        {
            button.doClick();
        }

        toolbar.add(button);
        toolbar.validate();
        repaint();
    }

    /**
     * TODO Use the factory to abstract shape creation
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
    **/
    public void mouseClicked(MouseEvent evt)
    {

        if (panel.contains(evt.getX(), evt.getY()))
        {
        	Graphics2D g2 = (Graphics2D) panel.getGraphics();
            switch (selected) {
                case CIRCLE -> {
                    Circle c = new Circle(evt.getX(), evt.getY());
                    c.draw(g2);
                    this.shapes.add(c);
                }
                case TRIANGLE -> {
                    Triangle t = new Triangle(evt.getX(), evt.getY());
                    t.draw(g2);
                    this.shapes.add(t);
                }
                case SQUARE -> {
                    Square s = new Square(evt.getX(), evt.getY());
                    s.draw(g2);
                    this.shapes.add(s);
                }
                default -> logger.log(new LogRecord(Level.WARNING, "No shape named " + selected));
            }
        }
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
    **/
    public void mouseEntered(MouseEvent evt)
    {
    	
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
    **/
    public void mouseExited(MouseEvent evt)
    {
    	label.setText(" ");
    	label.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    public void mousePressed(MouseEvent evt)
    {
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    public void mouseReleased(MouseEvent evt)
    {
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
    **/
    public void mouseDragged(MouseEvent evt)
    {
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
    **/
    public void mouseMoved(MouseEvent evt)
    {
    	modifyLabel(evt);
    }
    
    private void modifyLabel(MouseEvent evt) {
    	label.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
    **/
    private class ShapeActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
        	// Itère sur tous les boutons
            for (Shapes shape : buttons.keySet()) {
                JButton btn = buttons.get(shape);
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    selected = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }
}
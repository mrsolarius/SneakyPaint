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
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Serial;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.*;

import edu.uga.miage.m1.polygons.gui.dto.ShapeDTO;
import edu.uga.miage.m1.polygons.gui.dto.ShapesDTO;
import edu.uga.miage.m1.polygons.gui.persistence.JSONExportVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLExportVisitor;
import edu.uga.miage.m1.polygons.gui.deserialization.Format;
import edu.uga.miage.m1.polygons.gui.deserialization.ShapeDeserialization;
import edu.uga.miage.m1.polygons.gui.shapes.*;


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

        // add top bar menu items file and edit
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        JMenuItem saveAsXML = new JMenuItem("Save as XML");
        JMenuItem saveAsJSON = new JMenuItem("Save as JSON");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");
        fileMenu.add(saveAsXML);
        fileMenu.add(saveAsJSON);
        fileMenu.add(load);
        fileMenu.add(exit);
        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.add(clear);
        setJMenuBar(menuBar);
        
        // add callbacks to menu items
        saveAsXML.addActionListener((e)-> saveAsXML());
        saveAsJSON.addActionListener((e) -> saveAsJSON());
        load.addActionListener((e) -> loadFile());
        exit.addActionListener((e) -> System.exit(0));
        clear.addActionListener((e) -> clearShapes());

        
        // Fills the panel
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        
        // Add shapes in the menu
        addShape(JDrawingFrame.Shapes.SQUARE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(JDrawingFrame.Shapes.TRIANGLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(JDrawingFrame.Shapes.CIRCLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));


        setPreferredSize(new Dimension(400, 400));
    }

    private void clearShapes() {
        shapes.clear();
        panel.repaint();
    }

    private void loadFile() {
        this.importFileShapes(GUIHelper.generateImportMenu(this));
    }

    private void saveAsXML() {
        String xml = new XMLExportVisitor().export(shapes.toArray(new SimpleShape[0]));
        this.saveFile(xml,"xml");
    }

    private void saveAsJSON() {
        String json = new JSONExportVisitor().export(shapes.toArray(new SimpleShape[0]));
        this.saveFile(json,"json");
    }

    private void importFileShapes(String path){
        try (FileInputStream fstream = new FileInputStream(path)) {
            String fileContent = new String(fstream.readAllBytes());
            ShapesDTO shapes = null;
            if (path.endsWith(".json")){
                shapes = ShapeDeserialization.deserialize(fileContent, Format.JSON);
                this.drawDTOShapes(shapes);
            }
            else if (path.endsWith(".xml")){
                shapes = ShapeDeserialization.deserialize(fileContent, Format.XML);
                this.drawDTOShapes(shapes);
            }
            else{
                logger.log(new LogRecord(Level.WARNING,"No import format selected"));
            }
        } catch (Exception e) {
            logger.log(new LogRecord(Level.WARNING,"Error while loading file"));
        }
    }

    private void drawDTOShapes(ShapesDTO shapes){
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        for(ShapeDTO s : shapes.getShapes()){
            SimpleShape shape = s.toEntity();
            shape.draw(g2);
            this.shapes.add(shape);
        }
    }

    /**
     * save file with content and extention
     */
    private void saveFile(String content, String extention){
        File file = new File("shapes."+extention);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
        } catch (Exception e) {
            logger.log(new LogRecord(Level.SEVERE, "Error while saving file"));
        }
        if (fileWriter!= null) {
            try {
                fileWriter.close();
            } catch (Exception e) {
                logger.log(new LogRecord(Level.SEVERE, "Error while closing file"));
            }
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
            for (Map.Entry<JDrawingFrame.Shapes, JButton> shape : buttons.entrySet()) {
                JButton btn = buttons.get(shape.getKey());
                if (evt.getActionCommand().equals(shape.getKey().toString())) {
                    btn.setBorderPainted(true);
                    selected = shape.getKey();
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }
}
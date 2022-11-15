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


import edu.uga.miage.m1.polygons.gui.whiteboard.WhiteBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class JDrawingFrame extends JFrame implements MouseMotionListener, MouseListener
{
    private enum Shapes {SQUARE, TRIANGLE, CIRCLE,SELECT}
    @Serial
    private static final long serialVersionUID = 1L;
    private final JToolBar toolbar;
    private Shapes selected;
    private final transient WhiteBoard whiteBoard;
    private final JLabel label;
    private final transient ActionListener reusableActionListener = new ShapeActionListener();
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
        toolbar.setOrientation(JToolBar.VERTICAL);
        whiteBoard = WhiteBoard.getInstance();
        whiteBoard.addMouseMotionListener(this);
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
        JMenuItem group = new JMenuItem("Group");
        JMenuItem ungroup = new JMenuItem("Ungroup");
        JMenuItem elevate = new JMenuItem("Elevate");
        JMenuItem lower = new JMenuItem("Lower");
        JMenuItem delete = new JMenuItem("Delete");
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
        editMenu.add(group);
        editMenu.add(ungroup);
        editMenu.add(elevate);
        editMenu.add(lower);
        editMenu.add(delete);
        setJMenuBar(menuBar);
        
        // add callbacks to menu items
        saveAsXML.addActionListener(e-> whiteBoard.saveAsXml());
        saveAsJSON.addActionListener(e -> whiteBoard.saveAsJson());
        //load.addActionListener(e -> loadFile());
        exit.addActionListener(e -> System.exit(0));
        clear.addActionListener(e -> whiteBoard.clearShapes());
        group.addActionListener(e -> whiteBoard.groupSelectedShapes());
        ungroup.addActionListener(e -> whiteBoard.ungroupSelectedShapes());
        elevate.addActionListener(e -> whiteBoard.elevateSelectedShapes());
        lower.addActionListener(e -> whiteBoard.lowerSelectedShapes());
        delete.addActionListener(e -> whiteBoard.deleteSelectedShapes());

        
        // Fills the panel
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.WEST);
        add(whiteBoard, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        
        // Add shapes in the menu
        addShape(JDrawingFrame.Shapes.SQUARE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(JDrawingFrame.Shapes.TRIANGLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(JDrawingFrame.Shapes.CIRCLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));
        addShape(JDrawingFrame.Shapes.SELECT, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/mouse.png"))));


        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void pack() {
        super.pack();
        this.whiteBoard.getState().loaded();
    }

    /*
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

    //@Todo: refactor this method it's break cause of group of shapes
    private void importFileShapes(String path){
        try (FileInputStream fstream = new FileInputStream(path)) {
            String fileContent = new String(fstream.readAllBytes());
            ShapesDTO shapesDTO;
            if (path.endsWith(".json")){
                shapesDTO = ShapeDeserialization.deserialize(fileContent, Format.JSON);
                this.drawDTOShapes(shapesDTO);
            }
            else if (path.endsWith(".xml")){
                shapesDTO = ShapeDeserialization.deserialize(fileContent, Format.XML);
                this.drawDTOShapes(shapesDTO);
            }
            else{
                logger.log(new LogRecord(Level.WARNING,"No import format selected"));
            }
        } catch (Exception e) {
            logger.log(new LogRecord(Level.WARNING,"Error while loading file"));
        }
    }

    private void drawDTOShapes(ShapesDTO shapes){
        Graphics2D g2 = (Graphics2D) whiteBoard.getGraphics();
        for(ShapeDTO s : shapes.getShapes()){
            AbstractShape shape = s.toEntity(g2);
            shape.draw();
            this.shapes.add(shape);
        }
    }
     */

    /**
     * save file with content and extention
     */
    /*
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
    }*/




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

    /*
    private AbstractShape filterByCoordinate(int x, int y){
        for(AbstractShape s : shapes){
            if(s.contains(x,y)){
                return s;
            }
        }
        return null;
    }

    private void unselectAllShapes(){
        for(AbstractShape s : shapes){
            s.clickUnselect();
        }
    }


    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
    **/
    @Override
    public void mouseExited(MouseEvent evt)
    {
    	label.setText(" ");
    	label.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    @Override
    public void mousePressed(MouseEvent evt)
    {
        /*
        if (evt.getClickCount() == 2)
        {
            AbstractShape shape = filterByCoordinate(evt.getX(), evt.getY());
            if (shape != null)
            {
                unselectAllShapes();
                shape.clickSelect();
                shape.draw();
            }else {
                unselectAllShapes();
            }
        }*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
    **/
    @Override
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
                    switch (selected) {
                        case SQUARE -> whiteBoard.getState().addSquare();
                        case TRIANGLE -> whiteBoard.getState().addTriangle();
                        case CIRCLE -> whiteBoard.getState().addCircle();
                        case SELECT -> whiteBoard.getState().selectMode();
                    }
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }
}
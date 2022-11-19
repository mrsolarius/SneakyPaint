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
import edu.uga.miage.m1.polygons.gui.whiteboard.command.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.VERTICAL;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class JDrawingFrame extends JFrame implements MouseMotionListener, MouseListener, ComponentListener
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
        toolbar.setOrientation(VERTICAL);
        whiteBoard = WhiteBoard.getInstance();
        whiteBoard.addMouseMotionListener(this);
        label = new JLabel(" ", LEFT);

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
        JMenuItem group = new JMenuItem("Group (Ctrl+G)");
        JMenuItem ungroup = new JMenuItem("Ungroup (Ctrl+U)");
        JMenuItem elevate = new JMenuItem("Elevate (Ctrl+Up)");
        JMenuItem lower = new JMenuItem("Lower (Ctrl+Down)");
        JMenuItem delete = new JMenuItem("Delete (Suppr)");
        JMenuItem clear = new JMenuItem("Clear all");
        JMenuItem undo = new JMenuItem("Undo (Ctrl+Z)");
        JMenuItem redo = new JMenuItem("Redo (Ctrl+Y)");
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
        load.addActionListener(e -> whiteBoard.loadFile());
        exit.addActionListener(e -> System.exit(0));
        undo.addActionListener(e -> new UndoCommand(whiteBoard).execute());
        clear.addActionListener(e -> whiteBoard.clearShapes());
        group.addActionListener(e -> new GroupeCommand(whiteBoard).execute());
        ungroup.addActionListener(e -> new UngroupeCommand(whiteBoard).execute());
        elevate.addActionListener(e -> new ElevateCommand(whiteBoard).execute());
        lower.addActionListener(e -> new LowerCommand(whiteBoard).execute());
        delete.addActionListener(e -> new DeleteCommand(whiteBoard).execute());

        //register ctrl+z as undo envent
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        //register ctrl+y as redo envent
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redo");
        //register ctrl+g as group envent
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK), "group");
        //register ctrl+u as ungroup envent
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK), "ungroup");
        //register ctrl+up as elevate envent
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK), "elevate");
        //register ctrl+down as lower envent
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK), "lower");
        //register delete as delete envent
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");

        getRootPane().getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UndoCommand(whiteBoard).execute();
            }
        });
        getRootPane().getActionMap().put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new RedoCommand(whiteBoard).execute();
            }
        });
        getRootPane().getActionMap().put("group", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GroupeCommand(whiteBoard).execute();
            }
        });
        getRootPane().getActionMap().put("ungroup", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UngroupeCommand(whiteBoard).execute();
            }
        });
        getRootPane().getActionMap().put("elevate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ElevateCommand(whiteBoard).execute();
            }
        });
        getRootPane().getActionMap().put("lower", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LowerCommand(whiteBoard).execute();
            }
        });
        getRootPane().getActionMap().put("delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteCommand(whiteBoard).execute();
            }
        });


        
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

        addComponentListener(this);
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
        // no need to use this click event
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        // no need to use mouse pressed event
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // no need to use mouse release event
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // no need to use mouse enter event
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // no need to use mouse drag event
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

    @Override
    public void componentResized(ComponentEvent e) {
        this.whiteBoard.repaintAll();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // no need to use this event
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.whiteBoard.repaintAll();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // no need to do anything here
    }
}
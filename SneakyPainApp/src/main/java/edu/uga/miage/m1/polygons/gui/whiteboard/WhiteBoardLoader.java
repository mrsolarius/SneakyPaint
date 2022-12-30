package edu.uga.miage.m1.polygons.gui.whiteboard;

import edu.uga.miage.m1.libs.shapes.ShapeFactory;
import edu.uga.miage.m1.libs.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.deserialization.Format;
import edu.uga.miage.m1.polygons.gui.deserialization.ShapeDeserialization;
import edu.uga.miage.m1.libs.shapes.dto.ShapesDTO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class WhiteBoardLoader {

    private WhiteBoardLoader() {}

    /**
     * Open a file chooser to select a file to load
     * @return absolute path of the selected file
     */
    private static String openDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a saved file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON or XML files", "json", "xml"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    private static Format getFormat(String fileName) {
        if (fileName==null) {
            return null;
        }
        if (fileName.endsWith(".json")) {
            return Format.JSON;
        } else if (fileName.endsWith(".xml")) {
            return Format.XML;
        }
        return null;
    }

    /**
     * Load a file and return its content as a String
     * @param fileName absolute path of the file to load
     * @return content of the file
     */
    private static String readFile(String fileName) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static List<SimpleShape> load() {
        String fileName = openDialog();
        Format format = getFormat(fileName);
        if (format!=null){
            ShapesDTO shapesDTO = ShapeDeserialization.deserialize(readFile(fileName), format);
            return ShapeFactory.createShapesFromDTOs(shapesDTO.getShapes());
        }else {
            JOptionPane.showMessageDialog(null, "Invalid file");
        }
        return Collections.emptyList();
    }

}

package edu.uga.miage.m1.polygons.gui.whiteboard;

import edu.uga.miage.m1.polygons.gui.persistence.JSONExportVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLExportVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class WhiteBoardExporter {
    private static final Logger logger = Logger.getLogger(WhiteBoardExporter.class.getName());

    private WhiteBoardExporter() {
    }
    private static String exportToXML(List<SimpleShape> shapes){
        return new XMLExportVisitor().export(shapes.toArray(new SimpleShape[0]));
    }

    private static String exportToJSON(List<SimpleShape> shapes){
        return new JSONExportVisitor().export(shapes.toArray(new SimpleShape[0]));
    }

    private static void saveToFile(String content, String fileName){
        File file = new File(fileName);
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

    private static String openFileExplorer(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
    public static void saveAsJson(List<SimpleShape> shapes) {
        String content = exportToJSON(shapes);
        String fileName = openFileExplorer();
        if (fileName != null) {
            saveToFile(content, fileName+File.separator+"save.json");
        }
    }

    public static void saveAsXml(List<SimpleShape> shapes) {
        String content = exportToXML(shapes);
        String fileName = openFileExplorer();
        if (fileName != null) {
            saveToFile(content, fileName+File.separator+"save.xml");
        }
    }
}

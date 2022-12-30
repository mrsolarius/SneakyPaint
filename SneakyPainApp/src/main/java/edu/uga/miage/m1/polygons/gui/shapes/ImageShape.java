package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ImageShape extends AbstractShape{
    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Image image;
    private final BufferedImage imageBytes;

    ImageShape(int x, int y, int width, int height, int elevation, BufferedImage image) {
        super(x,y,width,height,elevation);
        this.imageBytes = image;
        this.image = toolkit.createImage(imageBytes.getSource());
    }
    ImageShape(int x, int y, BufferedImage image) {
        super(x, y);
        this.imageBytes = image;
        this.image = toolkit.createImage(imageBytes.getSource());
    }

    @Override
    public void draw(Graphics2D g) {
        // draw the image at the current position with the current size
        g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
        if (isSelected()) {
            this.drawSelection(g);
        }
    }

    public String getImageBase64() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Obtenir un ImageWriter
            ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
            // Obtenir un ImageOutputStream
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(baos);
            // Définir le ImageWriter et l'ImageOutputStream
            writer.setOutput(imageOutputStream);
            // Écrire l'image sur l'ImageOutputStream
            writer.write(imageBytes);
            // Récupérer l'image en byte[]
            byte[] imageBytes = baos.toByteArray();
            return encoder.encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

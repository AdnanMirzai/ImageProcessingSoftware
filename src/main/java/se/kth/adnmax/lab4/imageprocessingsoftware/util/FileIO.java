package se.kth.adnmax.lab4.imageprocessingsoftware.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileIO {

    public static Image readImage(File file) {
        try {
            return new Image(file.toURI().toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read image file", e);
        }
    }

    public static void writeImage(Image image, File file) throws IllegalArgumentException {
        if(image == null) throw new IllegalArgumentException("Image must not be null!");
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + file.getName(), e);
        }
    }

}

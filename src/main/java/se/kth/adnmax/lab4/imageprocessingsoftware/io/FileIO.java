package se.kth.adnmax.lab4.imageprocessingsoftware.io;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import se.kth.adnmax.lab4.imageprocessingsoftware.util.ImagePixelsConverter;

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

    public static void writeImage(Image image, File file) {
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + file.getName(), e);
        }
    }

    public Image loadImage(File file) {
        Image image = FileIO.readImage(file);
        int[][]pixels = ImagePixelsConverter.imageToPixels(image);
        saveOriginal(pixels);
        return image;
    }

    public void saveImage(Image image, File file) {
        FileIO.writeImage(image, file);
    }

}

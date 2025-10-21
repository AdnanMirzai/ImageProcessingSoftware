package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import javafx.scene.image.Image;

import java.io.File;

public class FileIO {

    public static Image readImage(File file) {
        try {
            return new Image(file.toURI().toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read image file", e);
        }
    }

}

package se.kth.adnmax.lab4.imageprocessingsoftware.controller;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

import se.kth.adnmax.lab4.imageprocessingsoftware.model.ImageProcessorFacade;
import se.kth.adnmax.lab4.imageprocessingsoftware.util.ImagePixelsConverter;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.ImageProcessorView;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.IviewListener;

public class ImageProcessorController implements IviewListener {

    private final Stage stage;
    private final ImageProcessorView view;
    private final ImageProcessorFacade model;
    private final FileChooser fileChooser;

    public ImageProcessorController(Stage stage, ImageProcessorView view, ImageProcessorFacade model) {
        this.stage = stage;
        this.view = view;
        this.model = model;
        view.setViewListener(this);

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Image files", "*.png", ".jpg", "*.bmp");
        fileChooser.getExtensionFilters().add(filter);
    }

    @Override
    public void onInvertSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No image file selected. Load an image to begin processing.");
            return;
        }

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] newPixels = model.processInvert(pixels);

        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(newPixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onGreyScaleSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] newPixels = model.processGreyScale(pixels);
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(newPixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onWindowLevelSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        double window = view.getWindow();
        double level = view.getLevel();
        int[][] newPixels = model.processWindowLevel(pixels, window, level);
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(newPixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onBlurSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] newPixels = model.processBlur(pixels);
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(newPixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onSharpenSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] newPixels = model.processSharpen(pixels);
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(newPixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onLoadImageSelected() {
        File file = fileChooser.showOpenDialog(stage);

        if(file != null) {
            Image image = model.loadImage(file);
            view.displayImage(image);
            //Update histogram
            int[][] pixels = ImagePixelsConverter.imageToPixels(image);
            model.saveOriginal(pixels);
            int[][] histogramValues = model.calculateHistogram(pixels);
            view.updateHistogram(histogramValues);
        }
        else {
            view.showAlertInfo("No file selected");
        }
    }

    @Override
    public void onSaveImageSelected() {
        File file = fileChooser.showSaveDialog(stage);
        if(file != null) {
            Image currentImage = view.getCurrentImage();
            model.saveImage(currentImage, file);
            view.showAlertInfo("Image was saved successfully!");
        } else {
            view.showAlertInfo("No file selected");
        }
    }

    @Override
    public void onResetSelected() {
        int[][] original = model.getOriginal();
        if(original == null) return;

        Image originalImage = ImagePixelsConverter.pixelsToImage(original);
        view.displayImage(originalImage);
        int[][] histogramValues = model.calculateHistogram(original);
        view.updateHistogram(histogramValues);
    }

}
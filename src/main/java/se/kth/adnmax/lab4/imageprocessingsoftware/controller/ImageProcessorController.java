package se.kth.adnmax.lab4.imageprocessingsoftware.controller;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.adnmax.lab4.imageprocessingsoftware.model.FileIO;
import se.kth.adnmax.lab4.imageprocessingsoftware.model.ImageProcessorFacade;
import se.kth.adnmax.lab4.imageprocessingsoftware.util.ImagePixelsConverter;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.ImageProcessorView;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.IviewListener;

import java.io.File;

public class ImageProcessorController implements IviewListener {

    private Stage stage;
    private ImageProcessorView view;
    private ImageProcessorFacade model;
    private FileChooser fileChooser;

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

        //convert viewdata to pixels
        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);

        //goes throgh model
        int[][] invertedPixels = model.processInvert(pixels);

        //convert back, update view
        Image invertedImage = ImagePixelsConverter.pixelsToImage(invertedPixels);
        view.displayImage(invertedImage);
        int[][] histogramValues = model.calculateHistogram(pixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onGreyScaleSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        //convert viewdata to pixels
        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);

        //goes throgh model
        int[][] newPixels = model.greyScale(pixels);

        //convert back, update view
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(pixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onWindowLevelSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        //convert viewdata to pixels
        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        double window = view.getWindow();
        double level = view.getLevel();

        //goes throgh model
        int[][] newPixels = model.processWindowLevel(pixels, window, level);

        //convert back, update view
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(pixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onBlurSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        //convert viewdata to pixels
        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);

        //goes throgh model
        int[][] newPixels = model.processBlur(pixels);

        //convert back, update view
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(pixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onSharpenSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.showAlertInfo("No file selected. Load an image to begin processing.");
            return;
        }

        //convert viewdata to pixels
        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);

        //goes throgh model
        int[][] newPixels = model.processSharpen(pixels);

        //convert back, update view
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        int[][] histogramValues = model.calculateHistogram(pixels);
        view.updateHistogram(histogramValues);
    }

    @Override
    public void onLoadImageSelected() {
        File file = fileChooser.showOpenDialog(stage);

        if(file != null) {
            Image image = FileIO.readImage(file);
            view.displayImage(image);
            //Update histogram
            int[][] pixels = ImagePixelsConverter.imageToPixels(image);
            int[][] histogramValues = model.calculateHistogram(pixels);
            view.updateHistogram(histogramValues);
        }
        else {
            view.showAlertInfo("No file selected");
        }
    }

    public void onHistogramUpdate() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) {
            view.clearHistogram();
            return;
        }

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] histogramValues = model.calculateHistogram(pixels);
        view.updateHistogram(histogramValues);
    }

    public void setInitialImage(Image image) {
        view.displayImage(image);
    }
}

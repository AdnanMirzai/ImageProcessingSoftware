package se.kth.adnmax.lab4.imageprocessingsoftware.controller;

import javafx.scene.image.Image;
import se.kth.adnmax.lab4.imageprocessingsoftware.model.ImageProcessorFacade;
import se.kth.adnmax.lab4.imageprocessingsoftware.util.ImagePixelsConverter;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.ImageProcessorView;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.IviewListener;

public class ImageProcessorController implements IviewListener {

    private final ImageProcessorView view;
    private final ImageProcessorFacade model;

    public ImageProcessorController(ImageProcessorView view, ImageProcessorFacade model) {
        this.view = view;
        this.model = model;
        view.setViewListener(this);
    }

    @Override
    public void onInvertSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) return;

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] invertedPixels = model.processInvert(pixels);

        Image invertedImage = ImagePixelsConverter.pixelsToImage(invertedPixels);
        view.displayImage(invertedImage);
    }

    @Override
    public void onGreyScaleSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) return;

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] newPixels = model.greyScale(pixels);

        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
    }

    @Override
    public void onWindowLevelSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) return;

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        double window = view.getWindow();
        double level = view.getLevel();

        int[][] newPixels = model.processWindowLevel(pixels, window, level);

        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
        view.updateHistogram();
    }

    @Override
    public void onResetSelected() {
        int[][] original = model.getOriginal();
        if(original == null) return;

        Image originalImage = ImagePixelsConverter.pixelsToImage(original);
        view.displayImage(originalImage);
        view.updateHistogram();
    }

    @Override
    public void onBlurSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) return;

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] newPixels = model.processBlur(pixels);

        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
    }

    @Override
    public void onSharpenSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) return;

        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);
        int[][] newPixels = model.processSharpen(pixels);

        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
    }

    public void setInitialImage(Image image) {
        int[][] pixels = ImagePixelsConverter.imageToPixels(image);
        model.saveOriginal(pixels);
        view.displayImage(image);
    }
}
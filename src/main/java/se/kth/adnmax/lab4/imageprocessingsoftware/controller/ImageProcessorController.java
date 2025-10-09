package se.kth.adnmax.lab4.imageprocessingsoftware.controller;

import javafx.scene.image.Image;
import se.kth.adnmax.lab4.imageprocessingsoftware.model.ImageProcessorFacade;
import se.kth.adnmax.lab4.imageprocessingsoftware.util.ImagePixelsConverter;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.ImageProcessorView;
import se.kth.adnmax.lab4.imageprocessingsoftware.view.IviewListener;

public class ImageProcessorController implements IviewListener {

    private ImageProcessorView view;
    private ImageProcessorFacade model;

    public ImageProcessorController(ImageProcessorView view, ImageProcessorFacade model) {
        this.view = view;
        this.model = model;
        view.setViewListener(this);
    }

    @Override
    public void onInvertSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) return;

        //convert viewdata to pixels
        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);

        //goes throgh model
        int[][] invertedPixels = model.processInvert(pixels);

        //convert back, update view
        Image invertedImage = ImagePixelsConverter.pixelsToImage(invertedPixels);
        view.displayImage(invertedImage);
    }

    @Override
    public void onGreyScaleSelected() {
        Image currentImage = view.getCurrentImage();
        if (currentImage == null) return;

        //convert viewdata to pixels
        int[][] pixels = ImagePixelsConverter.imageToPixels(currentImage);

        //goes throgh model
        int[][] newPixels = model.greyScale(pixels);

        //convert back, update view
        Image newImage = ImagePixelsConverter.pixelsToImage(newPixels);
        view.displayImage(newImage);
    }

    public void setInitialImage(Image image) {
        view.displayImage(image);
    }
}

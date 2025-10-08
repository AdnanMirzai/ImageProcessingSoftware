package se.kth.adnmax.lab4.imageprocessingsoftware.model;

public class ImageProcessorFacade {
    private InvertColors invertProcessor;

    public ImageProcessorFacade() {
        invertProcessor = new InvertColors();
    }

    public int[][] processInvert(int[][] pixels) {
        return invertProcessor.process(pixels);
    }
}

package se.kth.adnmax.lab4.imageprocessingsoftware.model;

public class ImageProcessorFacade {
    private IPixelProcessor invertProcessor;
    private IPixelProcessor greyScale;

    public ImageProcessorFacade() {
        invertProcessor = new InvertColors();
        greyScale = new GreyScale();
    }

    public int[][] processInvert(int[][] pixels) {
        return invertProcessor.process(pixels);
    }

    public int[][] greyScale(int[][] pixels) {
        return greyScale.process(pixels);
    }
}

package se.kth.adnmax.lab4.imageprocessingsoftware.model;

public class ImageProcessorFacade {
    private IPixelProcessor invertProcessor;
    private IPixelProcessor greyScale;
    private IPixelProcessor blur;
    private IPixelProcessor sharpen;

    public ImageProcessorFacade() {
        invertProcessor = new InvertColors();
        greyScale = new GreyScale();
        blur = new Blur();
        sharpen = new Sharpen();
    }

    public int[][] processInvert(int[][] pixels) {
        return invertProcessor.process(pixels);
    }

    public int[][] greyScale(int[][] pixels) {
        return greyScale.process(pixels);
    }

    public int[][] processBlur(int[][] pixels) {
        return blur.process(pixels);
    }

    public int[][] processSharpen(int[][]pixels) {
        return sharpen.process(pixels);
    }
}

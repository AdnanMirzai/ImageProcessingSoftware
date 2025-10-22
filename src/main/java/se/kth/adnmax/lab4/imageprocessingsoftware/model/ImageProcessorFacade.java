package se.kth.adnmax.lab4.imageprocessingsoftware.model;

public class ImageProcessorFacade {

    private int[][] originalMatix;

    private IPixelProcessor invertProcessor;
    private IPixelProcessor greyScale;
    private IPixelProcessor blur;
    private IPixelProcessor sharpen;
    private HistogramCalculator histogramCalc;

    public void saveOriginal(int [][] matix) {
        this.originalMatix = matix;
    }

    public int[][] getOriginal() {
        return deepCopy(originalMatix);
    }


    public ImageProcessorFacade() {
        invertProcessor = new InvertColors();
        greyScale = new GreyScale();
        blur = new Blur();
        sharpen = new Sharpen();
    }

    public int[][] processInvert(int[][] pixels) {
        return invertProcessor.process(pixels);
    }

    public int[][] processGreyScale(int[][] pixels) {
        return greyScale.process(pixels);
    }

    public int[][] processWindowLevel(int[][] pixels, double window, double level) {
        WindowLevel windowLevel = new WindowLevel(window, level);
        return windowLevel.process(pixels);
    }

    public int[][] processBlur(int[][] pixels) {
        return blur.process(pixels);
    }

    public int[][] processSharpen(int[][]pixels) {
        return sharpen.process(pixels);
    }

    public int[][] calculateHistogram(int[][] pixels) {
        return HistogramCalculator.calculateHistogram(pixels);
    }

    private int[][] deepCopy(int[][] original) {
        if(original==null) return null;
        int h = original.length;
        int w = original[0].length;
        int[][] copy = new int[h][w];
        for(int i=0; i<h; i++) {
            System.arraycopy(original[i], 0, copy[i], 0,w);
        }
        return copy;
    }
}

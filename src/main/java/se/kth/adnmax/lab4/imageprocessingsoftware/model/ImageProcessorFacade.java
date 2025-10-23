package se.kth.adnmax.lab4.imageprocessingsoftware.model;

/**
 * ImageProcessorFacade is a facade for all model functionality.
 * <p>
 * Stores original pixel matrix and gives functionallity to retrive.
 * Hides details and complexity while offering all methods that controller needs
 * for processing images. Such as greyscale and invert.
 * Provides acess to model classes that implements {@link IPixelProcessor}
 *
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public class ImageProcessorFacade {

    private int[][] originalMatix;    

    private final IPixelProcessor invertProcessor;
    private final IPixelProcessor greyScale;
    private final IPixelProcessor blur;
    private final IPixelProcessor sharpen;

    /**
     * Constructs a new ImageProcessorFacade and initializes the implementations of the pixel processing.
     */
    public ImageProcessorFacade() {
        invertProcessor = new InvertColors();
        greyScale = new GreyScale();
        blur = new Blur();
        sharpen = new Sharpen();
    }

    /**
     * Saves a deep copy of the provided pixel matrix as the original image data.
     *
     * @param matix The 2D pixel matrix to be saved as the original.
     */
    public void saveOriginal(int [][] matix) {
        this.originalMatix = deepCopy(matix);
    }

    /**
     * Returns a deep copy of the original pixel matrix.
     *
     * @return A deep copy of the original pixel matrix
     */
    public int[][] getOriginal() {
        return deepCopy(originalMatix);
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

    /**
     * Creates a deep copy of a 2D int matrix.
     *
     * @param original The original matrix.
     * @return A deep copy of the matrix.
     */
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
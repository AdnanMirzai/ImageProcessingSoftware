package se.kth.adnmax.lab4.imageprocessingsoftware.model;

/**
 * IPixelProcessor defines the contract for class that performs a processing operation on a 2D pixel matrix.
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public interface IPixelProcessor {

    /**
     * @param originalPixels The 2D int array representing the image pixels to be processed.
     * @return A new 2D int array after the processing operation has been applied.
     */
    int[][] process(int[][] originalPixels);
}
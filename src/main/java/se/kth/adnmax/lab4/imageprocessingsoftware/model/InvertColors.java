package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.model.PixelConverter.*;

/**
 * InvertColors is an implementation of {@link IPixelProcessor} that performs
 * a color inversion operation on a pixel matrix.
 * It calculates the inverse intensity for Red, Green, and Blue
 * of every pixel, creating the invers of the original image.
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public class InvertColors implements IPixelProcessor {

    /**
     * Processes the pixel matrix to invert the colors of the image.
     * The method iterates through every pixel, leaves opacity unchanged
     * and calculates the inverted intensity for the Red, Green, and Blue channels
     * {@code 255 - original intensity}.
     *
     * @param originalPixels 2D integer array representing the image
     * @return A new 2D integer array containing the inverted pixels.
     */
    public int[][] process(int[][] originalPixels) {

        int width = originalPixels.length;
        int height = originalPixels[0].length;
        int[][] invertedMatrix = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = originalPixels[x][y];
                int alpha = getAlpha(pixel);
                int invRed = 255 - getRed(pixel);
                int invGreen = 255 - getGreen(pixel);
                int invBlue = 255 - getBlue(pixel);

                int invertedPixel = toArgbPixel(alpha, invRed, invGreen, invBlue);
                invertedMatrix[x][y] = invertedPixel;
            }
        }

        return invertedMatrix;
    }
}

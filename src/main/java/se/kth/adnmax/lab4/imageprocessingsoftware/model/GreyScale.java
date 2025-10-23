package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.model.PixelConverter.*;

/**
 * A class that implements {@link IPixelProcessor} to convert original image to greyscale.
 *
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public class GreyScale implements IPixelProcessor {

    /**
     * Processes an image represented by 2D matrix, applies greyscale, leaves opacity unchanged
     *
     * @param originalPixels a 2D matix, representing ARGB pixel values of original image
     * @return a new int[][] matrix where each pixel has same avarage RGB values
     */
    @Override
    public int[][] process(int[][] originalPixels) {
        int width = originalPixels.length;
        int height = originalPixels[0].length;
        int[][] greyMatrix = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = originalPixels[x][y];
                int alpha = getAlpha(pixel);

                int  avg = (getRed(pixel) + getBlue(pixel) + getGreen(pixel))/3;

                int greyPixel = toArgbPixel(alpha, avg, avg, avg);
                greyMatrix[x][y] = greyPixel;
            }
        }
        return greyMatrix;
    }

}

package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.model.PixelConverter.*;

/**
 * Sharpen is an implementation of {@link IPixelProcessor} that sharpens the image
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public class Sharpen implements IPixelProcessor {
    /**
     * Executes the sharpening algorithm on the input pixel matrix using the unsharp mask principle.
     * In 4 steps: <p>
     * 1. Blurring the original image using blur method. <p>
     * 2. Calculating the difference (mask) between the original and blurred image for each color. <p>
     * 3. Adding the difference (mask) back to the original image to enhance edges. <p>
     * 4. Clamping the resulting color values to the valid range [0, 255]. <p>
     *
     * @param originalPixels The two-dimensional integer array representing the image
     * pixels (ARGB format).
     * @return A new two-dimensional integer array containing the sharpened pixels.
     */
    @Override
    public int[][] process(int[][] originalPixels) {
        int width = originalPixels.length;
        int height = originalPixels[0].length;
        int[][] sharpenMatrix = new int[width][height];

        Blur blurProcessor = new Blur();
        int[][] blurPixels = blurProcessor.process(originalPixels);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = originalPixels[x][y];
                int blurPixel = blurPixels[x][y];
                int alpha = getAlpha(pixel); // leave alpha (opacity) unchanged

                int diffRed = getRed(pixel) - getRed(blurPixel);
                int diffGreen = getGreen(pixel) - getGreen(blurPixel);
                int diffBlue = getBlue(pixel) - getBlue(blurPixel);

                int addRed = getRed(pixel) + diffRed;
                int addGreen = getGreen(pixel) + diffGreen;
                int addBlue = getBlue(pixel) + diffBlue;

                int sharpenRed = clamp(addRed);
                int sharpenGreen = clamp(addGreen);
                int sharpenBlue = clamp(addBlue);

                int sharpenPixel = toArgbPixel(alpha, sharpenRed, sharpenGreen, sharpenBlue);
                sharpenMatrix[x][y] = sharpenPixel;
            }
        }

        return sharpenMatrix;
    }

    private int clamp(int value) {
        if (value > 255) {
            return 255;
        }
        if (value < 0) {
            return 0;
        }
        return value;
    }
}

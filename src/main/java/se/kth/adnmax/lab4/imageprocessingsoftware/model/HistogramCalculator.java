package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.model.PixelConverter.*;
/**
 * HistogramCalculator provides static method for computing the
 * color histogram of an image represented by a 2D pixel matrix.
 * The resulting histogram counts the frequency of each intensity value 0-255
 * for Red, Green, and Blue.
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public class HistogramCalculator {

    /**
     * Calculates the color histogram for an image represented by a 2D pixel matrix.
     * Independently for the Red, Green, and Blue.
     *
     * @param pixels The 2D integer array, each int represents a ARGB pixel.
     * @return A 2D integer array of size 256x3 representing the histogram:
     * Rows (0-255) correspond to the color intensity value.
     * Column 0 - RED.
     * Column 1 - GREEN.
     * Column 2 - BLUE.
     */
    public static int[][] calculateHistogram(int[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        int[][] histogramValues = new int[256][3];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = pixels[x][y];
                int r = getRed(pixel);
                int g = getGreen(pixel);
                int b = getBlue(pixel);

                histogramValues[r][0]++;
                histogramValues[g][1]++;
                histogramValues[b][2]++;
            }
        }
        return histogramValues;
    }

}

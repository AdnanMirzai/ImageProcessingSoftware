package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.util.PixelConverter.*;

public class histogramCalculator {

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

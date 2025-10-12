package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.util.PixelConverter.*;
import static se.kth.adnmax.lab4.imageprocessingsoftware.util.PixelConverter.toArgbPixel;

public class Sharpen implements IPixelProcessor{
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

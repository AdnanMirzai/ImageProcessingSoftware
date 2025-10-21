package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.util.PixelConverter.*;

public class GreyScale implements IPixelProcessor {

    @Override
    public int[][] process(int[][] originalPixels) {
        int width = originalPixels.length;
        int height = originalPixels[0].length;
        int[][] greyMatrix = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = originalPixels[x][y];
                int alpha = getAlpha(pixel); // leave alpha (opacity) unchanged

                int  avg = (getRed(pixel) + getBlue(pixel) + getGreen(pixel))/3;

                int greyPixel = toArgbPixel(alpha, avg, avg, avg);
                greyMatrix[x][y] = greyPixel;
            }
        }

        return greyMatrix;
    }

}

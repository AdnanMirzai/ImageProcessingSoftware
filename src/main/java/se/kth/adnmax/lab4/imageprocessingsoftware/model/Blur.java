package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.util.PixelConverter.*;

public class Blur implements IPixelProcessor {
    // Viktmatris
    private static final int[][] WEIGHTS = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
    };

    @Override
    public int[][] process(int[][] originalPixels) {
        int width = originalPixels.length;
        int height = originalPixels[0].length;
        int[][] blurMatrix = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float sumRed = 0;
                float sumGreen = 0;
                float sumBlue = 0;
                int totalWeight = 0;


                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1 ; dy++) {
                        int nx = x + dx;
                        int ny = y + dy;

                        // Om den sökta pixeln ligger utanför bildens dimensioner hoppa över den. Alltså utanför (0,0) till (width, height)
                        if(nx < 0 || nx >= width || ny < 0 || ny >= height)
                            continue;

                        int pixel = originalPixels[nx][ny];
                        int weight = WEIGHTS[dx + 1][dy + 1];

                        sumRed += getRed(pixel) * weight;
                        sumGreen += getGreen(pixel) * weight;
                        sumBlue += getBlue(pixel) * weight;
                        totalWeight += weight;
                    }
                }

                int alpha = getAlpha(originalPixels[x][y]); // leave alpha (opacity) unchanged
                int avgRed = Math.round(sumRed/totalWeight);
                int avgGreen = Math.round(sumGreen/totalWeight);
                int avgBlue = Math.round(sumBlue/totalWeight);

                int blurPixel = toArgbPixel(alpha, avgRed, avgGreen, avgBlue);
                blurMatrix[x][y] = blurPixel;
            }
        }

        return blurMatrix;
    }

}

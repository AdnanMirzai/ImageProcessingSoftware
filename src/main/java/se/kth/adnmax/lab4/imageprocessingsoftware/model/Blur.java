package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.model.PixelConverter.*;

/**
 * A class that implements {@link IPixelProcessor} to convert original image to
 * blurry image using a 3x3 weighted matrix
 *
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public class Blur implements IPixelProcessor {

    private static final int[][] WEIGHTS = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
    };

    /**
     * Processes an image represented by 2D matrix, applies blur, leaves opacity unchanged
     *
     * @param originalPixels a 2D matix, representing ARGB pixel values of original image
     * @return a new int[][] matrix where each pixel value is a weighted average of the
     * closest 3x3 neighboring pixles values including itself.
     */
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
                        int neighborX = x + dx;
                        int neighborY = y + dy;

                        //Skip the edges to avoid array out of bounds exception
                        if(neighborX < 0 || neighborX >= width || neighborY < 0 || neighborY >= height)
                            continue;

                        int pixel = originalPixels[neighborX][neighborY];
                        int weight = WEIGHTS[dx + 1][dy + 1];

                        sumRed += getRed(pixel) * weight;
                        sumGreen += getGreen(pixel) * weight;
                        sumBlue += getBlue(pixel) * weight;
                        totalWeight += weight;
                    }
                }

                int alpha = getAlpha(originalPixels[x][y]); //leave opacity unchanged
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

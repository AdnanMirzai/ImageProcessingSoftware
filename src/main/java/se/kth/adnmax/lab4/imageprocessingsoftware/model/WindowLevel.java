package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.util.PixelConverter.*;

public class WindowLevel implements IPixelProcessor {

    private final double window;
    private final double level;

    public WindowLevel(double window, double level) {
        this.window = window;
        this.level = level;
    }

    @Override
    public int[][] process(int[][] originalPixels) {
        int width = originalPixels.length;
        int height = originalPixels[0].length;
        int[][] windowLevelMatrix = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = originalPixels[x][y];
                int alpha = getAlpha(pixel);

                int red   = applyWindowLevel(getRed(pixel), window, level);
                int green = applyWindowLevel(getGreen(pixel), window, level);
                int blue  = applyWindowLevel(getBlue(pixel), window, level);

                int adjustedPixel = toArgbPixel(alpha, red, green, blue);
                windowLevelMatrix[x][y] = adjustedPixel;
            }
        }

        return windowLevelMatrix;
    }

    private int applyWindowLevel(int value, double window, double level) {
        double k = 255.0 / window;

        double scaled;
        if (value < level) {
            scaled = 0;
        } else if (value > level + window) {
            scaled = 255;
        } else {
            scaled = k * (value - level); // k * hur stor offset det är från level
        }

        return (int) scaled;
    }

}

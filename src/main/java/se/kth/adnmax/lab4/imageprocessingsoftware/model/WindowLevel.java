package se.kth.adnmax.lab4.imageprocessingsoftware.model;

import static se.kth.adnmax.lab4.imageprocessingsoftware.model.PixelConverter.*;
/**
 * WindowLevel is an implementation of {@link IPixelProcessor} that adjusts
 * the contrast and brightness of an image using the Window/Level
 *
 * @author Adnan Mirzai
 * @author Max Ihrén
 */
public class WindowLevel implements IPixelProcessor {

    private final double window;
    private final double level;

    /**
     * Constructs a WindowLevel processor with the window width and center level.
     *
     * @param window The window width controlling the contrast.
     * @param level The center level controlling the brightness.
     */
    public WindowLevel(double window, double level) {
        this.window = window;
        this.level = level;
    }

    /**
     * Executes the Window/Level transformation on the input pixel matrix.
     * applied to the Red, Green, and Blue color of every pixel, opacity remains unchanged.
     *
     * @param originalPixels 2D int array representing the image pixels
     * @return A new 2D int array containing the pixels after the Window/Level process.
     */
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

    /**
     * Applies the Window/Level formula to a single color intensity value.
     * Values outside the defined range [level, level + window] are set to 0 or 255.
     * Values inside the range are scaled to fit the output range [0, 255].
     *
     * @param value color intensity value (0-255) to be transformed.
     * @param window The window width (W) used for contrast scaling.
     * @param level The bottom/start level (L) used for brightness offset.
     * @return The transformed color intensity value, clamped between 0 and 255.
     */
    private int applyWindowLevel(int value, double window, double level) {
        double k = 255.0 / window;

        double scaled;
        if (value < level) {
            scaled = 0;
        } else if (value > level + window) {
            scaled = 255;
        } else {
            scaled = k * (value - level); //k * how big the offset is from level
        }

        return (int) scaled;
    }

}

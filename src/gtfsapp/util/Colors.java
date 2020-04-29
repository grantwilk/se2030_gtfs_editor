package gtfsapp.util;

import javafx.scene.paint.Color;

/**
 * A utility class for handling colors
 * @author Grant Wilk
 */
public class Colors {

    /**
     * The regular expression for a properly formatted color
     */
    public static final String COLOR_REGEX = "^[#]?[0-9a-fA-F]{6}";

    /**
     * The maximum value of an 8-bit color channel
     */
    public static final double EIGHT_BIT_MAX = 255;

    /**
     * Creates a new JavaFX color from a color string
     * @param colorString - the color string in #RRGGBB or RRGGBB form
     * @return a new color object
     */
    public static Color fromString(String colorString) {

        // throw an exception if the color string is invalidly formatted
        if (!colorString.matches(COLOR_REGEX)) {
            throw new IllegalArgumentException("Invalidly formatted color string.");
        }

        // remove the # if it exists at the head of the color string
        if (colorString.charAt(0) == '#') {
            colorString = colorString.substring(1);
        }

        // convert the colors to rgb channels
        int red = Integer.parseInt(colorString.substring(0,2), 16);
        int green = Integer.parseInt(colorString.substring(2,4), 16);
        int blue = Integer.parseInt(colorString.substring(4,6), 16);

        // create a new color and return it
        return new Color(red / EIGHT_BIT_MAX, green / EIGHT_BIT_MAX, blue / EIGHT_BIT_MAX, 1);

    }

    /**
     * Converts a JavaFX color to a hex string
     * @param color - the color to convert
     * @return the color as a hex string
     */
    private String toString(Color color) {
        return String.format(
                "#%02X%02X%02X",
                (int) (color.getRed() * EIGHT_BIT_MAX),
                (int) (color.getGreen() * EIGHT_BIT_MAX),
                (int) (color.getBlue() * EIGHT_BIT_MAX)
        );
    }

    /**
     * Determines whether a color string is validly formatted
     * @param colorString - the color string to test
     */
    public static boolean isValidString(String colorString) {
        return colorString.matches(COLOR_REGEX);
    }

}

package my_project.view;

import my_project.Config;

import java.awt.*;

public class MainView {
    private static double SCALE = 1.0;
    private static double OFFSET_X = 0.0;
    private static double OFFSET_Y = 0.0;
    private static double SCREEN_OFFSET_X = 0.0;
    private static double SCREEN_OFFSET_Y = 0.0;

    public MainView() {

    }
    public static double getScale() {
        return SCALE;
    }
    public static void setScale(double scale) {
        SCALE = scale;
    }
    public static double getOffsetX() {
        return OFFSET_X;
    }
    public static void setOffsetX(double offsetX) {
        OFFSET_X = offsetX;
    }
    public static double getOffsetY() {
        return OFFSET_Y;
    }
    public static void setOffsetY(double offsetY) {
        OFFSET_Y = offsetY;
    }
    public static double getScreenOffsetX() {
        return SCREEN_OFFSET_X;
    }
    public static void setScreenOffsetX(double screenOffsetX) {
        SCREEN_OFFSET_X = screenOffsetX;
    }
    public static double getScreenOffsetY() {
        return SCREEN_OFFSET_Y;
    }
    public static void setScreenOffsetY(double screenOffsetY) {
        SCREEN_OFFSET_Y = screenOffsetY;
    }

    /**
     * OFFSETS the camera to follow the given position
     * @param center wether the camera should be centered on the given position
     */
    public static void follow(double x, double y, boolean center) {
        OFFSET_X = x;
        OFFSET_Y = y;
        if (center) {
            SCREEN_OFFSET_X = Config.WINDOW_WIDTH/2;
            SCREEN_OFFSET_Y = Config.WINDOW_HEIGHT/2;
        }
    }
    /**
     * Translates the given position By the GLOBAL OFFSET and
     * SCALE, to portray its position according to the current Camera Position
     * on the X-Axis
     * @param x Value to be translated and scaled on the X-Axis
     */
    public static double translateAndScaleX(double x) {
        return (x + OFFSET_X)* SCALE + SCREEN_OFFSET_X;
    }
    /**
     * Translates the given position By the GLOBAL OFFSET and
     * SCALE, to portray its position according to the current Camera Position
     * on the Y-Axis
     * @param y Value to be translated and scaled on the Y-Axis
     */
    public static double translateAndScaleY(double y) {
        return (y + OFFSET_Y)* SCALE + SCREEN_OFFSET_Y;
    }
    /**
     * Scales the given Value by the GLOBAL SCALE of the current view
     * @param v Value to be scaled
     */
    public static double scale(double v) {
        return v * SCALE;
    }


}



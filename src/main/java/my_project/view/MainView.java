package my_project.view;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.MainController;
import my_project.control.modeControl.*;
import my_project.model.CoreClasses.SettingsModel;
import my_project.view.javafx.BackendDeveloperAcces;
import my_project.view.modeView.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Main rendering Class of the project
 * <br><br>
 * Joshua Becker
 */
public class MainView {
    private BackendDeveloperAcces backendDeveloperAcces;
    private JFrame frame;
    private HashMap<String, ModeView> modeViews;
    private MainController mainController;

    private static double SCALE = 5.0;
    private static double OFFSET_X = 0.0;
    private static double OFFSET_Y = 0.0;
    private static double SCREEN_OFFSET_X = 0.0;
    private static double SCREEN_OFFSET_Y = 0.0;


    public MainView(MainController mainController) {
        this.mainController = mainController;
        HashMap<String, ModeControl> modeControls = mainController.getModeControls();

        backendDeveloperAcces = new BackendDeveloperAcces(mainController);
        frame = mainController.createJFrame(backendDeveloperAcces.getContentPane());

        modeViews = new HashMap<>();
        modeViews.put("map", new MapModeView((MapModeControl)modeControls.get("map")));
        modeViews.put("village", new VillageModeView((VillageModeControl)modeControls.get("village")));
        modeViews.put("travel", new TravelModeView((TravelModeControl)modeControls.get("travel")));
        modeViews.put("dungeon", new DungeonModeView((DungeonModeControl)modeControls.get("dungeon")));

    }

    public void draw(DrawTool drawTool) {
        modeViews.get(mainController.getMode()).draw(drawTool);
        drawTool.setCurrentColor(0,0,0,(int)((SettingsModel.getBrightness())*2.55));
        drawTool.drawFilledRectangle(0,0,Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT);
    }


    //Getter and Setters
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
        centerCamera(center);
    }
    public static void follow(double x, double y) {
        follow(x, y, false);
    }
    public static void moveCamera(double x, double y) {
        moveCameraX(x);
        moveCameraY(y);
    }
    public static void moveCameraX(double x) {
        OFFSET_X += x;
    }
    public static void moveCameraY(double y) {
        OFFSET_Y += y;
    }
    public static void centerCamera(boolean center) {
        if (center) {
            SCREEN_OFFSET_X = Config.WINDOW_WIDTH/2;
            SCREEN_OFFSET_Y = Config.WINDOW_HEIGHT/2;
        }else {
            SCREEN_OFFSET_X = 0;
            SCREEN_OFFSET_Y = 0;
        }
    }
    public static boolean isCameraCentered() {
        return SCREEN_OFFSET_X == Config.WINDOW_WIDTH/2 && SCREEN_OFFSET_Y == Config.WINDOW_HEIGHT/2;
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
    public BackendDeveloperAcces getBackEndDeveloperAcces() {
        return backendDeveloperAcces;
    }

}



package my_project.control;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Modes.DungeonMode.DungeonModeControl;
import my_project.Modes.LoadingMode.LoadingModeControl;
import my_project.Modes.MapMode.MapModeControl;
import my_project.Modes.ModeControl;
import my_project.Modes.StartMode.startModeControl;
import my_project.Settings.SettingsController;
import my_project.Modes.TravelMode.TravelModeControl;
import my_project.Modes.VillageMode.VillageModeControl;
import my_project.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Main Controlling Class of the project
 * <br><br>
 * Joshua Becker
 */
public class MainController extends GraphicalObject {

    // Attributes and Strings
    private static String currentMode;

    // References
    private MainView mainView;
    private SettingsController settingsController = new SettingsController(this);

    // Data structures
    private HashMap<String, ModeControl> modeControls;

    // Constructor
    public MainController() {
        currentMode = "start";
        modeControls = new HashMap<>(); // create Hashmap to cantain all controllers of game modes
        // add mode controllers to Hashmap
        modeControls.put("start", new startModeControl());
        modeControls.put("map", new MapModeControl());
        modeControls.put("village", new VillageModeControl());
        modeControls.put("travel", new TravelModeControl());
        modeControls.put("dungeon", new DungeonModeControl());
        modeControls.put("loading", new LoadingModeControl(this));

        mainView = new MainView(this);

        for (ModeControl mode :  modeControls.values()) {
            mode.setMainController(this);
        }
        modeControls.get(currentMode).setActive(true);
    }
    @Override
    public void draw(DrawTool drawTool) {
        mainView.draw(drawTool);
    }
    @Override
    public void update(double dt) {
        double fps = 1/dt;
        modeControls.get(currentMode).update(dt);
        mainView.getBackEndDeveloperAcces().setFPS(fps);
    }
    public void setMode(String mode) {
        if (modeControls.containsKey(mode)) {
            modeControls.get(currentMode).setActive(false);
            currentMode = mode.toLowerCase();
            modeControls.get(currentMode).setActive(true);
        }else{
            System.err.println("mode not found: " + mode);
        }
    }

    public void loadMode(String mode) {
        if (modeControls.containsKey(mode)) {
            String previous = currentMode;
            modeControls.get(currentMode).setActive(false);
            currentMode = "loading";
            modeControls.get(currentMode).setActive(true);
            ((LoadingModeControl)modeControls.get(currentMode)).loadMode(previous, mode);

        }else{
            System.err.println("mode not found: " + mode);
        }
    }

    public String getMode() {
        return currentMode;
    }

    public HashMap<String, ModeControl> getModeControls() {
        return modeControls;
    }

    public void processInput(String c){
        switch (c) {
            case "settings" : settingsController.getSettingsFrame().setVisible(true); break;
            case "zoomIn" : MainView.setScale(MainView.getScale() + 1); break;
            case "zoomOut" : MainView.setScale(MainView.getScale() - 1); System.out.println("out"); break;
            case "centerCamera" : MainView.centerCamera(!MainView.isCameraCentered()); break;
        }
    }

    public JFrame createJFrame(Container contentPane){
        return createJFrame(contentPane, 300, 400, true);
    }
    public JFrame createJFrame(Container contentPane, boolean visible){
        return createJFrame(contentPane, 300, 400, visible);
    }
    public JFrame createJFrame(Container contentPane, int width, int height){
        return createJFrame(contentPane, width, height);
    }
    public JFrame createJFrame(Container contentPane, int width, int height, boolean visible) {
        JFrame localFrame = new JFrame();
        localFrame.setContentPane(contentPane);
        localFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        localFrame.setPreferredSize(new Dimension(width, height));
        localFrame.pack();
        localFrame.setVisible(visible);
        return localFrame;
    }

}

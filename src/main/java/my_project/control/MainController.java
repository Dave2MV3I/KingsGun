package my_project.control;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.modeControl.*;
import my_project.view.MainView;
import my_project.view.modeView.*;

import java.util.HashMap;

public class MainController extends GraphicalObject {
    private MainView mainView;
    private static String currentMode;
    private HashMap<String, ModeControl> modeControls;
    public MainController() {
        currentMode = "map";
        modeControls = new HashMap<>();
        modeControls.put("map", new MapModeControl());
        modeControls.put("village", new VillageModeControl());
        modeControls.put("travel", new TravelModeControl());
        modeControls.put("dungeon", new DungeonModeControl());

        mainView = new MainView(this);
    }
    @Override
    public void draw(DrawTool drawTool) {
        mainView.draw(drawTool);
    }
    @Override
    public void update(double dt) {
        modeControls.get(currentMode).update(dt);
    }
    public void setMode(String mode) {
        modeControls.get(currentMode).setActive(false);
        currentMode = mode.toLowerCase();
        modeControls.get(currentMode).setActive(true);
    }
    public String getMode() {
        return currentMode;
    }

    public HashMap<String, ModeControl> getModeControls() {
        return modeControls;
    }
}

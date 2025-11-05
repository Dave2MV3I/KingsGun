package my_project.control;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.modeControl.*;
import my_project.view.MainView;
import my_project.view.modeView.*;

import java.util.HashMap;

public class MainController extends GraphicalObject {
    private static final MainView MAIN_VIEW = new MainView();
    private static String currentMode;
    private HashMap<String, ModeControl> modeControls;
    private HashMap<String, ModeView> modeViews;
    public MainController() {
        currentMode = "map";
        modeControls = new HashMap<>();
        modeControls.put("map", new MapModeControl());
        modeControls.put("village", new VillageModeControl());
        modeControls.put("travel", new TravelModeControl());
        modeControls.put("dungeon", new DungeonModeControl());

        modeViews = new HashMap<>();
        modeViews.put("map", new MapModeView((MapModeControl)modeControls.get("map")));
        modeViews.put("village", new VillageModeView((VillageModeControl)modeControls.get("village")));
        modeViews.put("travel", new TravelModeView((TravelModeControl)modeControls.get("travel")));
        modeViews.put("dungeon", new DungeonModeView((DungeonModeControl)modeControls.get("dungeon")));
    }
    @Override
    public void draw(DrawTool drawTool) {
        modeViews.get(currentMode).draw(drawTool);
    }
    @Override
    public void update(double dt) {
        modeControls.get(currentMode).update(dt);
    }
    public void setMode(String mode) {
        currentMode = mode;
    }
}

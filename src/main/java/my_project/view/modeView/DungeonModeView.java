package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.modeControl.DungeonModeControl;
import my_project.control.modeControl.ModeControl;

public class DungeonModeView extends ModeView {
    public DungeonModeView(DungeonModeControl modeControl) {
        super(modeControl);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(200, 140, 100, 255);
        drawTool.drawFilledRectangle(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        super.draw(drawTool);
    }
}

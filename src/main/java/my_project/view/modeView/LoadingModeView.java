package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.modeControl.LoadingModeControl;
import my_project.control.modeControl.ModeControl;

import java.awt.*;

public class LoadingModeView extends ModeView<LoadingModeControl> {
    private ModeView loadedView;
    public LoadingModeView(LoadingModeControl modeControl) {
        super(modeControl);
    }
    public void setLoadedView(ModeView loadedView) {
        this.loadedView = loadedView;
    }
    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        if (loadedView != null) {
            loadedView.draw(drawTool);
        }
        drawTool.setCurrentColor(new Color(0,0,0, (int)((1-(modeControl.getTimer()/3)) * 255)));
        drawTool.drawFilledRectangle(0,0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        drawTool.formatText("Arial", Font.BOLD, 100);
        drawTool.drawText(0,100, "Loading " + modeControl.getLoadedControl().getClass().getSimpleName());
    }
}

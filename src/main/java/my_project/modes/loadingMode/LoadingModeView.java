package my_project.modes.loadingMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.modes.ModeView;

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
        double progressionFactor = (modeControl.getTimer()/ modeControl.getTransitionTime());
        super.draw(drawTool);
        if (loadedView != null) {
            loadedView.draw(drawTool);
        }
        double loadingValue = progressionFactor < 0.5 ? (progressionFactor*2) : (1-((progressionFactor-0.5)*2));
        drawTool.setCurrentColor(new Color(0,0,0, (int)(loadingValue * 255)));
        drawTool.drawFilledRectangle(0,0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        drawTool.formatText("Arial", Font.BOLD, 100);
        if (loadedView != null) {
            drawTool.drawText(0, 100, "Loading " + modeControl.getLoadedControl().getClass().getSimpleName());
        }
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawFilledRectangle(50,Config.WINDOW_HEIGHT - 300,(Config.WINDOW_WIDTH-100) * progressionFactor, 50);
    }

}

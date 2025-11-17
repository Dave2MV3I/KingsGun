package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.modeControl.ModeControl;
import my_project.control.modeControl.TravelModeControl;
import my_project.model.Graphics.Texture;

public class TravelModeView extends ModeView {
    private Texture roadTexture;
    public TravelModeView(TravelModeControl modeControl) {
        super(modeControl);
        roadTexture = new Texture("road.png");
    }
    @Override
    public void draw(DrawTool drawTool) {
        for (int i = 0; i < 100; i++) {
            roadTexture.drawToWidth(drawTool, 0, 0 - i * roadTexture.getHeightRelativeToWidth(Config.WINDOW_WIDTH), Config.WINDOW_WIDTH);
        }
    }

    public void update(double dt){

    }
}

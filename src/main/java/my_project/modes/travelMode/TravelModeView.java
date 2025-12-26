package my_project.modes.travelMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.view.MainView;
import my_project.modes.ModeView;

public class TravelModeView extends ModeView<TravelModeControl> {
    private Texture roadTexture;
    public double yPos;
    public TravelModeView(TravelModeControl modeControl) {

        super(modeControl);
        roadTexture = new Texture("road_3.png");
        yPos = 600;
        MainView.setScale(roadTexture.getScaleRelativeToWidth(Config.WINDOW_WIDTH));
    }
    @Override
    public void draw(DrawTool drawTool) {
        for (int i = 0; i < 100; i++) {
            roadTexture.autoDraw(drawTool, 0, i * 32, 256);
        }
        if (modeControl.getBandit() != null) {
            modeControl.getBandit().draw(drawTool);
        }

        modeControl.getCarriage().draw(drawTool);

    }

}

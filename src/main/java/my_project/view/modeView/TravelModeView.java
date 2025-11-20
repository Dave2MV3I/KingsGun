package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.modeControl.ModeControl;
import my_project.control.modeControl.TravelModeControl;
import my_project.model.Graphics.Texture;
import my_project.model.TravelMode.Carriage;
import my_project.view.MainView;

public class TravelModeView extends ModeView {
    private Texture roadTexture;
    public double yPos;
    private double scale;
    private Carriage carriage;
    public TravelModeView(TravelModeControl modeControl) {

        super(modeControl);
        roadTexture = new Texture("road_3.png");
        carriage = ((TravelModeControl)modeControl).getCarriage();
        yPos = 600;
        MainView.setScale(roadTexture.getScaleRelativeToWidth(Config.WINDOW_WIDTH));
    }
    @Override
    public void draw(DrawTool drawTool) {
        for (int i = 0; i < 100; i++) {
            roadTexture.autoDraw(drawTool, 0, i * 32, 256);
        }
        carriage.draw(drawTool);
    }

}

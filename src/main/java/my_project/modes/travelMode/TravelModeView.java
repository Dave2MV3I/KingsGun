package my_project.modes.travelMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.view.MainView;
import my_project.modes.ModeView;
/**View Class for the Travel Mode
 * By Mykhailo Badasian
 */
public class TravelModeView extends ModeView<TravelModeControl> {
    private Texture roadTexture;
    public double yPos;
    public TravelModeView(TravelModeControl modeControl) {

        super(modeControl);
        roadTexture = new Texture("road_3.png");
        yPos = 2600;
        MainView.setScale(roadTexture.getScaleRelativeToWidth(Config.WINDOW_WIDTH));
    }
    @Override
    public void draw(DrawTool drawTool) {
        for (int i = 0; i < 100; i++) {
            roadTexture.autoDraw(drawTool, 0, yPos - i * 32, 256);
        }
        modeControl.getBandits().toFirst();
        while (modeControl.getBandits().hasAccess()){
            //if (bandits.getContent() != null){ //I added this, so there would be no errors when there isn't a bandit.
            modeControl.getBandits().getContent().draw(drawTool);
            modeControl.getBandits().next();
        }

        modeControl.getCarriage().draw(drawTool);

    }

}

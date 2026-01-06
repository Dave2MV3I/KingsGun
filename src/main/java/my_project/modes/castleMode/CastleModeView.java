package my_project.modes.castleMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.modes.ModeView;

public class CastleModeView extends ModeView<CastleModeControl> {
    private Texture texture = new Texture("castle Screen.png");
    public CastleModeView(CastleModeControl modeControl) {
        super(modeControl);
    }
    @Override
    public void draw(DrawTool dt){
        texture.drawToWidth(dt, 0, 0, Config.WINDOW_WIDTH);
    }
}

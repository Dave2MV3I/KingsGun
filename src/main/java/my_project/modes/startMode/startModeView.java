package my_project.modes.startMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.SpriteSheet;
import my_project.modes.ModeView;
import my_project.view.InputManager;
import my_project.view.Mouse;

public class startModeView extends ModeView<startModeControl> {
    SpriteSheet spriteSheet;
    public startModeView(startModeControl modeControl) {
        super(modeControl);
        spriteSheet = new SpriteSheet("startScreen.png", 1, 2);
    }

    @Override
    public void draw(DrawTool drawTool) {
        if (Mouse.getPosition().x < Config.WINDOW_WIDTH * 0.4){
            spriteSheet.setCurrent(1,0);
        } else {
            spriteSheet.setCurrent(0,0);
        }
        spriteSheet.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
        super.draw(drawTool);
    }
}

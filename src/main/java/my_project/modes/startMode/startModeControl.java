package my_project.modes.startMode;

import com.sun.tools.javac.Main;
import my_project.Config;
import my_project.control.MainController;
import my_project.modes.ModeControl;
import my_project.view.InputManager;
import my_project.view.Mouse;

import java.awt.event.KeyEvent;

public class startModeControl extends ModeControl<startModeView> {
    public startModeControl(MainController mainController) {
        super(mainController);

    }

    @Override
    protected void activate() {



    }
    @Override
    public void update(double dt){
        if (Mouse.getPosition().x < Config.WINDOW_WIDTH * 0.4 && Mouse.isDown(1)) {
            mainController.loadMode("map", "open map");
        }
    }

    @Override
    protected void deactivate() {

    }
}

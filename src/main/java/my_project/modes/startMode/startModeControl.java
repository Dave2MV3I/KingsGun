package my_project.modes.startMode;

import KAGO_framework.model.Sound;
import com.sun.tools.javac.Main;
import javafx.embed.swing.JFXPanel;
import my_project.Config;
import my_project.control.MainController;
import my_project.model.Player;
import my_project.modes.ModeControl;
import my_project.settings.SettingsModel;
import my_project.view.InputManager;
import my_project.view.Mouse;

import java.awt.event.KeyEvent;

/**Control Class for th Start Mode
 * By Joshua Becker
 */
public class startModeControl extends ModeControl<startModeView> {
    private JFXPanel fxPanel = new JFXPanel();
    private Sound openMap = new Sound("src/main/resources/sound/OpenMap.mp3","Open Map", false);

    public startModeControl(MainController mainController) {
        super(mainController);
    }

    @Override
    protected void activate() {
        mainController.setCurrentPlayer(new Player());


    }
    @Override
    public void update(double dt){
        openMap.setVolume(SettingsModel.getMasterVolume());
        if (Mouse.getPosition().x < Config.WINDOW_WIDTH * 0.4 && Mouse.isDown(1)) {
            mainController.loadMode("map", "open map");
            openMap.play();
        }
    }

    @Override
    protected void deactivate() {

    }
}

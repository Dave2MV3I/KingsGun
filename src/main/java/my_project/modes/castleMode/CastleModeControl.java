package my_project.modes.castleMode;

import my_project.control.MainController;
import my_project.modes.ModeControl;

public class CastleModeControl extends ModeControl<CastleModeView> {
    public CastleModeControl(MainController mainController) {
        super(mainController);
    }
    @Override
    public void update(double dt){
        super.update(dt);
        if (modeView.creditsBelowScreen()){
            mainController.loadMode("start");
        }
    }
    @Override
    protected void activate() {
    }
    @Override
    protected void deactivate() {
    }
}

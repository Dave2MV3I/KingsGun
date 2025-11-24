package my_project.control.modeControl;

import my_project.control.MainController;
import my_project.view.MainView;
import my_project.view.modeView.LoadingModeView;

public class LoadingModeControl extends ModeControl<LoadingModeView> {
    private String loadedMode;
    private ModeControl loadedControl;
    private MainController mainController;
    public LoadingModeControl(MainController mainController) {
        super();
        this.mainController = mainController;
    }

    @Override
    protected void activate() {

    }
    @Override
    public void update(double dt){
        super.update(dt);
        if (timer > 3.0) {
            mainController.setMode(loadedMode);
        }
    }

    @Override
    protected void deactivate() {

    }
    public ModeControl getLoadedControl() {
        return loadedControl;
    }
    public void loadMode(String mode) {
        loadedMode = mode;
        loadedControl = mainController.getModeControls().get(mode);
        loadedControl.setActive(true);
        modeView.setLoadedView(loadedControl.getModeView());
    }

}

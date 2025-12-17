package my_project.modes.loadingMode;

import my_project.control.MainController;
import my_project.modes.ModeControl;

public class LoadingModeControl extends ModeControl<LoadingModeView> {
    private String loadedMode;
    private String loadToMode;
    private ModeControl loadedControl;
    private ModeControl loadToControl;
    private boolean modeGotLoaded;
    private double transitionTime = 3; //In seconds
    public LoadingModeControl(MainController mainController) {
        super(mainController);
    }

    @Override
    protected void activate() {
        modeGotLoaded = false;
    }
    @Override
    public void update(double dt){
        super.update(dt);
        if (timer > transitionTime) {
            mainController.setMode(loadedMode, false);
        }else if (timer > transitionTime/2){
            activateLoadedMode(loadToMode);
        }
    }

    @Override
    protected void deactivate() {

    }
    public double getTransitionTime() {
        return transitionTime;
    }
    public ModeControl getLoadedControl() {
        return loadedControl;
    }
    public void loadMode(String modeTo) {
        loadToMode = modeTo;
    }
    public void loadMode(String modeFrom, String modeTo) {
        loadedMode = modeFrom;
        loadToMode = modeTo;
    }
    private void activateLoadedMode(String modeTo) {
        if (!modeGotLoaded) {
            loadedMode = modeTo;
            loadedControl = mainController.getModeControls().get(modeTo);
            loadedControl.setActive(true);
            modeView.setLoadedView(loadedControl.getModeView());
            modeGotLoaded = true;
        }
    }

}

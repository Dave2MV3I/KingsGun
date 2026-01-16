package my_project.modes.mapMode;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.control.MainController;
import my_project.modes.ModeControl;
import my_project.modes.villageMode.VillageModeControl;
import my_project.modes.villageMode.Village;

/**
 *
 */
public class MapModeControl extends ModeControl<MapModeView> {
    private List<Village> villages;
    private VillageModeControl villageModeControl;

    public MapModeControl(MainController mainController) {
        super(mainController);
    }

    @Override
    protected void deactivate() {
    }
    @Override
    protected void activate() {
        villageModeControl = (VillageModeControl) mainController.getModeControls().get("village");
        villages = villageModeControl.getVillages();
        modeView.setVillages(villages);
        modeView.setAmountVillages(villageModeControl.getAmountVillage());
    }

    @Override
    public void update(double dt){
        super.update(dt);
        modeView.update(dt);
    }

    public MapModeView getMapModeView() {return modeView;}
    public MainController getMainController() {return mainController;}

}

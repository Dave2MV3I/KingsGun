package my_project.Modes.MapMode;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.Modes.ModeControl;
import my_project.Modes.VillageMode.VillageModeControl;
import my_project.Modes.VillageMode.Village;

/**
 *
 */
public class MapModeControl extends ModeControl<MapModeView> {
    private List<Village> villages;
    private VillageModeControl villageModeControl;

    public MapModeControl() {

    }

    @Override
    protected void deactivate() {
    }
    @Override
    protected void activate() {
        villageModeControl = (VillageModeControl) mainController.getModeControls().get("village");
        villages = villageModeControl.getVillages();
        modeView.setAmountVillages(villageModeControl.getAmountVillage());
    }

    public MapModeView getMapModeView() {return modeView;}

}

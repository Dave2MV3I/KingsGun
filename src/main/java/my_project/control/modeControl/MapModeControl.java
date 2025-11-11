package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.VillageMode.Village;
import my_project.view.modeView.MapModeView;

/**
 *
 */
public class MapModeControl extends ModeControl {
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
        ((MapModeView)modeView).setAmountVillages(villageModeControl.getAmountVillage());
    }

    public VillageModeControl getVillageControl() {return villageModeControl;}

}

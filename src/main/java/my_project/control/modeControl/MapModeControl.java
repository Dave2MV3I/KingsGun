package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Village;

/**
 *
 */
public class MapModeControl extends ModeControl {
    private List<Village> villages;

    public MapModeControl() {
        VillageModeControl villageController = (VillageModeControl) mainController.getModeControls().get("village");
        villages = villageController.getVillages();
    }

    @Override
    protected void deactivate() {
    }
    @Override
    protected void activate() {
    }

}

package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.VillageMode.Village;

/**
 * Tomole
 */
public class VillageModeControl extends ModeControl {
    private List<Village> villages;
    private int amountVillage;

    public VillageModeControl() {
        amountVillage = 5;

        setUpVillages();
    }

    /**
     * Instantiate Villages in a List
     */
    private void setUpVillages() {
        villages = new List<Village>();
        for (int i = 0; i < amountVillage; i++) {
            Village v = new Village();
            villages.append(v);
        }
    }

    public List<Village> getVillages() {return villages;}

    @Override
    protected void activate() {

    }
    @Override
    protected void deactivate() {

    }
}

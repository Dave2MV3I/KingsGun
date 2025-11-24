package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.VillageMode.Village;
import my_project.view.modeView.VillageModeView;

/**
 * Tomole
 */
public class VillageModeControl extends ModeControl<VillageModeView> {
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
    public int getAmountVillage() {return amountVillage;}

    @Override
    protected void activate() {

    }
    @Override
    protected void deactivate() {

    }
}

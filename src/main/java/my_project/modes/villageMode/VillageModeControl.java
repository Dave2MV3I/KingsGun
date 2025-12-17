package my_project.modes.villageMode;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.modes.ModeControl;
import my_project.control.MainController;


/**
 * Tomole
 */
public class VillageModeControl extends ModeControl<VillageModeView> {
    private List<Village> villages;
    private int amountVillage;
    private int currentVillage = 0;

    public VillageModeControl(MainController mainController) {
        super(mainController);
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
    public VillageModeView getVillageModeView() {return modeView;}
    public MainController getMainController() {return mainController;}

    @Override
    protected void activate() {
        modeView.activationVillage(villages.getContent());
    }
    @Override
    protected void deactivate() {

    }
}

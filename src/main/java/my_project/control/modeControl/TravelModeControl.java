package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.TravelMode.Archer;
import my_project.model.TravelMode.Bandit;
import my_project.model.TravelMode.Carriage;

public class TravelModeControl extends ModeControl {
    private Carriage carriage;
    private List<Bandit> bandits;
    public TravelModeControl() {
        carriage = new Carriage();
        bandits = new List<Bandit>();
    }
    @Override
    protected void deactivate() {
        bandits = new List<Bandit>();//delete all Bandits by creating a new empty List of Bandits
    }
    @Override
    protected void activate() {
        bandits.append(new Archer());
        System.out.println("created Bandit");
    }
}

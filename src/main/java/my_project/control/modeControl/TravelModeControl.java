package my_project.control.modeControl;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.TravelMode.Bandits.Archer;
import my_project.model.TravelMode.Bandits.Bandit;
import my_project.model.TravelMode.Carriage;

public class TravelModeControl extends ModeControl {
    private Carriage carriage;
    private List<Bandit> bandits;
    private double time;
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
    @Override
    public void update(double dt){
        time = time + dt;

        if (checkAndHandleCollision(bandits.getContent())){
            bandits.getContent().loseHP(100);
        }
    }

    public boolean checkAndHandleCollision(GraphicalObject gO){
        if (gO.collidesWith(carriage)) {
            return true;
        }
        return false;
    }
}

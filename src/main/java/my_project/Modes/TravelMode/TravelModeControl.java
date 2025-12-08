package my_project.Modes.TravelMode;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.Modes.ModeControl;
import my_project.Modes.TravelMode.Bandits.Archer;
import my_project.Modes.TravelMode.Bandits.Bandit;

public class TravelModeControl extends ModeControl<TravelModeView> {
    private Carriage carriage;
    private List<Bandit> bandits;
    private double time;
    public TravelModeControl() {
        carriage = new Carriage();
        bandits = new List<Bandit>();
    }
    @Override
    protected void deactivate() {

    }
    @Override
    protected void activate() {
        carriage = new Carriage();
        bandits = new List<Bandit>();//delete all Bandits by creating a new empty List of Bandits
        bandits.toFirst();
        bandits.append(new Archer());
        System.out.println("created Bandit");
    }
    @Override
    public void update(double dt){
        time = time + dt;
        carriage.update(dt);
        if (checkAndHandleCollision(bandits.getContent())){
            bandits.getContent().loseHP(100);

        }
        if (bandits.getContent() != null){ //I added this, so there would be no errors when there isn't a bandit.
            bandits.getContent().update(dt);
        }


    }

    public boolean checkAndHandleCollision(GraphicalObject gO){
        if (gO != null){
            if (gO.collidesWith(carriage)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public Carriage getCarriage(){
        return carriage;
    }
    public Bandit getBandit(){
        bandits.toFirst();
        return bandits.getContent();
    }
}

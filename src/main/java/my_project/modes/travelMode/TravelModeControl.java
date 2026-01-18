package my_project.modes.travelMode;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.control.MainController;
import my_project.modes.ModeControl;
import my_project.modes.travelMode.Bandits.Archer;
import my_project.modes.travelMode.Bandits.Bandit;

public class TravelModeControl extends ModeControl<TravelModeView> {
    private Carriage carriage;
    private List<Bandit> bandits;
    private double time;
    public TravelModeControl(MainController mainController) {
        super(mainController);
    }
    @Override
    protected void deactivate() {

    }
    @Override
    protected void activate() {

        carriage = new Carriage(this);
        carriage.setAmmo(mainController.getCurrentPlayer().getAmmo());
        bandits = new List<Bandit>();//delete all Bandits by creating a new empty List of Bandits

        bandits.toFirst();
        for (int i = 0; i < 500; i++) {
            int linkrecht = (int)(Math.random()*2);
            int obenunten = ((int)(Math.random() * 2)) * 2 - 1;
            bandits.append(new Archer(carriage, 1800 - i*40, linkrecht, obenunten));
            System.out.println("created Bandit");
        }
        //bandits.append(new Archer(carriage, 500, 0));
        //bandits.append(new Archer(carriage, 300, 1));
        //bandits.append(new Archer(carriage, 100, 1));
        //System.out.println("created Bandit");
    }
    @Override
    public void update(double dt){
        time = time + dt;
        carriage.update(dt);
        bandits.toFirst();
        while (bandits.hasAccess()){
            if (checkAndHandleCollision(bandits.getContent())){
                bandits.getContent().loseHP(100);

            }
            bandits.getContent().update(dt);
            if (bandits.getContent().hP <= 0){
                bandits.remove();
            }else bandits.next();

        }
        if (carriage.outOfBounds){
            mainController.loadMode("village", "enter Village");
        }
        if (carriage.getHP() <= 0){
            mainController.loadMode("start", "death");
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
    public List<Bandit> getBandits(){
        return bandits;
    }
}

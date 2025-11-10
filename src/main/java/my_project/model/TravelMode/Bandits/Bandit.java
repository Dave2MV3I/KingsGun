package my_project.model.TravelMode.Bandits;

import KAGO_framework.model.GraphicalObject;

public abstract class Bandit extends GraphicalObject {
    public double hP;

    public void loseHP(double damage){
        hP = hP - damage;
    }

    public double showHP(){
        return hP;
    }
}

package my_project.modes.travelMode.Ammunition;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.modes.travelMode.Bandits.Bandit;
import my_project.modes.travelMode.Carriage;

import java.awt.*;

public class ElectricAmmunition extends Ammunition {
    public ElectricAmmunition() {
        super(35, 10);
        this.color = new Color(0, 234, 255);
    }
    public void hit(Bandit bandit, List<Bandit> bandits, double hitX, double hitY) {
        bandit.loseHP(damage);
    }
}

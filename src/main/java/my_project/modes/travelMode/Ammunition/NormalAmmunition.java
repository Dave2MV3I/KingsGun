package my_project.modes.travelMode.Ammunition;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.modes.travelMode.Bandits.Bandit;

import java.awt.*;

public class NormalAmmunition extends Ammunition {
    public NormalAmmunition() {
        super(60, 8);
        this.color = new Color(255, 245, 192);
    }
    public void hit(Bandit bandit, List<Bandit> bandits, double hitX, double hitY) {
        bandit.loseHP(damage);
    }
}

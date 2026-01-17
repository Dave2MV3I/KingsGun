package my_project.modes.travelMode.Ammunition;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.modes.travelMode.Bandits.Bandit;

import java.awt.*;

public class ExplosiveAmmunition extends Ammunition {
    public ExplosiveAmmunition() {
        super(20, 20);
        this.color = new Color(255, 60, 0);
    }
    public void hit(Bandit bandit, List<Bandit> bandits, double hitX, double hitY) {
        bandit.loseHP(damage);
        for (int i = 0; i < 10; i++) {
            shots ++;
            shoot(Math.random()* Math.PI*2, hitX, hitY, bandits, 100, 1, bandit);
        }
    }
}

package my_project.modes.travelMode.Ammunition;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.modes.travelMode.Bandits.Bandit;
import my_project.modes.travelMode.Carriage;

import java.awt.*;
/**By Joshua Becker
 */
public class ElectricAmmunition extends Ammunition {
    public ElectricAmmunition() {
        super(35, 10);
        this.color = new Color(44, 232, 245);
    }
    public void hit(Bandit bandit, List<Bandit> bandits, double hitX, double hitY) {
        bandits.toFirst();
        Bandit closest = null;
        while (bandits.hasAccess()){
            if(closest == null){
                closest = bandits.getContent();
            }else{
                if (bandits.getContent().getDistanceTo(hitX, hitY) < closest.getDistanceTo(hitX, hitY)){
                    closest = bandits.getContent();
                }
            }
            bandits.next();
        }
        double newDir = Math.atan2((closest.getY() - closest.getHeight()/2) - (hitY), (closest.getX() - closest.getWidth()/2) - (hitX));
        shots++;
        shoot(newDir, hitX, hitY, bandits, 150, 1, bandit);
        bandit.loseHP(damage);
    }
}

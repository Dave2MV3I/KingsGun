package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.modes.dungeonMode.DungeonPlayer;
import my_project.modes.travelMode.Ammunition.Ammunition;

public class Player {
    private double money;


    //private DungeonPlayer dungeonPlayer;
    private Stack<Ammunition> ammo;



    private int currentVillage;
    public Player() {
        money = 0;
        currentVillage = 0;
        ammo = new Stack<>();
    }

    public void addMoney(double money) {
        this.money += money;
    }
    public double getMoney() {
        return money;
    }

    public int getCurrentVillage() {
        return currentVillage;
    }

    public void setCurrentVillage(int currentVillage) {
        this.currentVillage = currentVillage;
    }

    public Stack<Ammunition> getAmmo() {
        return ammo;
    }

    public void setAmmo(Stack<Ammunition> ammo) {
        this.ammo = ammo;
    }

}

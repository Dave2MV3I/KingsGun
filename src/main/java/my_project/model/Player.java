package my_project.model;

import my_project.modes.dungeonMode.DungeonPlayer;

public class Player {
    private double money;
    //private DungeonPlayer dungeonPlayer;
    public Player() {
        money = 0;
    }

    public void addMoney(double money) {
        this.money += money;
    }
    public double getMoney() {
        return money;
    }
}

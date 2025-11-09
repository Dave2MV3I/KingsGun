package my_project.model.TravelMode;

import my_project.model.CoreClasses.GameObject;

public class Carriage extends GameObject {
    private AmmunitionInventory ammoInvent;
    private Gun gun;
    public Carriage() {
        ammoInvent = new AmmunitionInventory();
        gun = new Gun();
    }
}

package my_project.model;

public class Carriage extends GameObject{
    private AmmunitionInventory ammoInvent;
    private Gun gun;
    public Carriage() {
        ammoInvent = new AmmunitionInventory();
        gun = new Gun();
    }
}

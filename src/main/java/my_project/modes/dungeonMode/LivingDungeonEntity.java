package my_project.modes.dungeonMode;

import my_project.model.GameObject;
/**
 * Alive Gameobjects with properties practical for use in dungeon
 * by David Gregory Glusmann
 */
public abstract class LivingDungeonEntity extends DungeonEntity {
    protected double health = 100;

    public LivingDungeonEntity(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl);
    }

    public void damage(double damagePoints){
        this.health -= damagePoints;
    }
    public double getHealth(){return health;}
}

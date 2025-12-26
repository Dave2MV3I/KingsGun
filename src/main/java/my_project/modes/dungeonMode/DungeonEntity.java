package my_project.modes.dungeonMode;

import my_project.model.GameObject;

public abstract class DungeonEntity extends GameObject {
    protected DungeonModeControl control;
    protected double health = 100;
    public DungeonEntity(DungeonModeControl dungeonModeControl) {
        this.control = dungeonModeControl;
    }

    @Override
    protected boolean movementCondition() {
        return !(checkForWall(x, y) || checkForWall(x-16, y) || checkForWall(x, y-16) || checkForWall(x+16, y) || checkForWall(x, y+16));
    }
    private boolean checkForWall(double x, double y){
        if (control.getTileByCoord(x, y) == null){
            return false;
        }
        return (control.getTileByCoord(x, y).isSolid() && control.getTileByCoord(x, y).collidesWith(this));
    }

    public void damage(double damagePoints){
        this.health -= damagePoints;
    }
    public double getHealth(){return health;}
}

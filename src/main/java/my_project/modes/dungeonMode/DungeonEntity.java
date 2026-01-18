package my_project.modes.dungeonMode;

import my_project.model.GameObject;

public class DungeonEntity extends GameObject {
    protected DungeonModeControl control;

    public DungeonEntity(DungeonModeControl dungeonModeControl) {
        this.control = dungeonModeControl;
    }

    @Override
    protected boolean movementCondition() {
        if (checkForWall(x, y) || checkForWall(x-16, y) || checkForWall(x, y-16) || checkForWall(x+16, y) || checkForWall(x, y+16)){
            onCollision();
            return false;
        }
        return true;
    }
    private boolean checkForWall(double x, double y){
        if (control.getTileByCoord(x, y) == null){
            return false;
        }
        return (control.getTileByCoord(x, y).isSolid() && control.getTileByCoord(x, y).collidesWith(this));
    }
    /**
     This method is automatically executed when a gameobject collides with something.
     Every inheriting Class should @Override this function if they want something special to happen on collision.
     */
    protected void onCollision() {}
}

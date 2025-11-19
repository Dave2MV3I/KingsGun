package my_project.model.DungeonMode.Monsters;

import KAGO_framework.view.DrawTool;
import my_project.model.CoreClasses.GameObject;

/**
 * In each Dungeon spawns some type of Monster.
 * <br><br>
 * David Glusmann
 */

public abstract class Monster extends GameObject {
    public Monster(){
        //TODO David: Design the monsters with different appearances and get them to know their tasks in the enum Task with all tasks
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.drawFilledRectangle(this.x, this.y, 10, 10);
        drawTool.drawFilledRectangle(this.x+5, this.y+5, 10, 10);
    }

}

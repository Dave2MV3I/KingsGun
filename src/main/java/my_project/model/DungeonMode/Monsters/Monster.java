package my_project.model.DungeonMode.Monsters;

import KAGO_framework.view.DrawTool;
import my_project.model.CoreClasses.GameObject;
import my_project.model.DungeonMode.Tasks.Attack;
import my_project.model.Graphics.AnimatedSpriteSheet;

import java.awt.*;

/**
 * In each Dungeon spawns some type of Monster.
 * <br><br>
 * David Glusmann
 */

public abstract class Monster extends GameObject {
    protected Attack[] myAttacks;

    public Monster(Attack[] attacks){
        myAttacks = attacks;

        radius = 16;
        texture = new AnimatedSpriteSheet("testSlime.png", 1, 2);
        ((AnimatedSpriteSheet)texture).setFrameCooldownX(0.5);

        //TODO David: Design the monsters with different appearances and get them to know their tasks in the enum Task with all tasks
    }
    protected Monster(){
        //Ich habe das hier hin gemacht, sodass ich das Spiel starten kann - Joshua
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.drawFilledRectangle(this.x, this.y, 10, 10);
        drawTool.drawFilledRectangle(this.x+5, this.y+5, 10, 10);
        texture.autoDraw(drawTool, x, y, 32);
        drawTool.setCurrentColor(new Color(255, 0, 0));
        autoDrawHitbox(drawTool);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        texture.update(dt);
    }

}

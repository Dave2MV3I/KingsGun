package my_project.model.DungeonMode;

import my_project.model.CoreClasses.GameObject;
import my_project.model.CoreClasses.SpriteSheet;
import my_project.model.CoreClasses.Texture;

public class Player extends GameObject {
    public Player() {
        this.texture = new Texture("player.png");
    }
}

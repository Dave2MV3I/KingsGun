package my_project.model.DungeonMode;

import my_project.model.CoreClasses.GameObject;
import my_project.model.Graphics.Texture;

public class Player extends GameObject {
    public Player() {
        this.texture = new Texture("player.png");
    }
}

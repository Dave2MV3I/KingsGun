package my_project.modes.dungeonMode.Tiles;

import my_project.model.Graphics.SpriteSheet;
import my_project.modes.dungeonMode.Dungeon;
import my_project.model.Graphics.Texture;

public class TileDoor extends InteractiveTile{

    private int opening = 0;
    private double cooldown = 0.0;
    public TileDoor(int x, int y, Dungeon dungeon) {
        super(x, y, new SpriteSheet("Door.png", 1, 6), dungeon);

    }
    public void interact() {
        if (((SpriteSheet)texture).getCurrentX() == 5) {
            opening = -1;
        }else if(((SpriteSheet)texture).getCurrentX() == 0){
            opening = 1;
        }



    }
    @Override
    public void update(double dt){
        cooldown += dt;
        if(opening != 0 && cooldown >= 0.7){
            ((SpriteSheet)texture).setCurrentX((int)((((SpriteSheet)texture).getCurrentX() + opening)));
            cooldown = cooldown%0.7;
        }

        if (((SpriteSheet)texture).getCurrentX() == 5){
            setSolid(false);
            opening = 0;
        }else if(((SpriteSheet)texture).getCurrentX() == 0){
            setSolid(true);
            opening = 0;
        } else{
            setSolid(true);

        }
    }
}

package my_project.modes.dungeonMode.Tiles;

import my_project.model.Graphics.SpriteSheet;
import my_project.model.Graphics.Texture;
import my_project.modes.dungeonMode.Dungeon;

public class TileCrate extends InteractiveTile {
    private boolean isClosed = true;
    public TileCrate(int x, int y, Dungeon dungeon) {
        super(x, y, new SpriteSheet("crate.png", 1, 2), dungeon);
        ((SpriteSheet)texture).setCurrent(0,0);
    }

    @Override
    public void interact() {
        if (isClosed){
            isClosed = false;System.out.println("Chest interact");
            dungeon.getPlayer().increaseMoney(10);
            ((SpriteSheet)texture).setCurrent(1,0);
        }

    }
}

package my_project.modes.dungeonMode.Tiles;

import my_project.model.Graphics.SpriteSheet;
import my_project.model.Graphics.Texture;
import my_project.modes.dungeonMode.Dungeon;
/** Grid Based Tile for the Dungeon, to give the Player loot
 * By joshua Becker
 */
public class TileCrate extends InteractiveTile {
    private boolean isClosed = true;
    public TileCrate(int x, int y, Dungeon dungeon) {
        super(x, y, new SpriteSheet("crate.png", 1, 2), dungeon);
        setSolid(true);
        spreadFactor = 0.03;
        ((SpriteSheet)texture).setCurrent(0,0);
    }

    @Override
    public void interact() {
        if (isClosed){
            isClosed = false;
            dungeon.getPlayer().increaseMoney(8 + (int)(Math.random() * 20));
            ((SpriteSheet)texture).setCurrent(1,0);
        }

    }
}

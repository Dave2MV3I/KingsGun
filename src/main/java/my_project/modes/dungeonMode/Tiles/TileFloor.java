package my_project.modes.dungeonMode.Tiles;

import my_project.modes.dungeonMode.Dungeon;
import my_project.model.Graphics.SpriteSheet;

public class TileFloor extends Tile {
    public TileFloor(int x, int y, Dungeon dungeon) {
        super(x, y, new SpriteSheet("FloorTileSheet.png", 1, 9), dungeon);
        ((SpriteSheet)texture).setRandom();
    }
}

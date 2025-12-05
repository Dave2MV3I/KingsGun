package my_project.model.DungeonMode.Tiles;

import my_project.model.DungeonMode.Dungeon;
import my_project.model.Graphics.SpriteSheet;
import my_project.model.Graphics.Texture;

public class TileFloor extends Tile {
    public TileFloor(int x, int y, Dungeon dungeon) {
        super(x, y, new SpriteSheet("FloorTileSheet.png", 1, 9), dungeon);
        ((SpriteSheet)texture).setRandom();
    }
}

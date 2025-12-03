package my_project.model.DungeonMode.Tiles;

import my_project.model.DungeonMode.Dungeon;
import my_project.model.Graphics.SpriteSheet;
import my_project.model.Graphics.TileSheet;

public class TileWall extends Tile {
    public TileWall(int x, int y, Dungeon dungeon) {
        super(x, y, new TileSheet("WallTilesheet.png"), dungeon);
        setSolid(true);
    }

}

package my_project.model.DungeonMode.Tiles;

import my_project.model.Graphics.SpriteSheet;
import my_project.model.Graphics.TileSheet;

public class TileWall extends Tile {
    public TileWall(int x, int y) {
        super(x, y, new TileSheet("WallTileSheet.png"));

    }
}

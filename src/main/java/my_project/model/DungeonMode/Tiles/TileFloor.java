package my_project.model.DungeonMode.Tiles;

import my_project.model.DungeonMode.Dungeon;
import my_project.model.Graphics.Texture;

public class TileFloor extends Tile {
    public TileFloor(int x, int y, Dungeon dungeon) {
        super(x, y, new Texture("FloorTile.png"), dungeon);
    }
}

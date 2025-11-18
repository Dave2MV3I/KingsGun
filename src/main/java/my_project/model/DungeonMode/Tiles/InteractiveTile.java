package my_project.model.DungeonMode.Tiles;

import my_project.model.DungeonMode.Dungeon;
import my_project.model.Graphics.Texture;
import my_project.model.Graphics.TileSheet;

public abstract class InteractiveTile extends Tile{
    public InteractiveTile(int x, int y, Texture texture, Dungeon dungeon) {
        super(x, y, texture, dungeon);
    }
    public abstract void interact();

}

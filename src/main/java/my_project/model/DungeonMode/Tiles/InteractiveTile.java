package my_project.model.DungeonMode.Tiles;

import my_project.model.Graphics.Texture;
import my_project.model.Graphics.TileSheet;

public abstract class InteractiveTile extends Tile{
    public InteractiveTile(int x, int y, Texture texture) {
        super(x, y, texture);
    }
    public abstract void interact();

}

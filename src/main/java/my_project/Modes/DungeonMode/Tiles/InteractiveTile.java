package my_project.Modes.DungeonMode.Tiles;

import my_project.Modes.DungeonMode.Dungeon;
import my_project.model.Graphics.Texture;

public abstract class InteractiveTile extends Tile{
    public InteractiveTile(int x, int y, Texture texture, Dungeon dungeon) {
        super(x, y, texture, dungeon);
    }
    public abstract void interact();

}

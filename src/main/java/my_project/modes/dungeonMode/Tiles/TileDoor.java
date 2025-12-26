package my_project.modes.dungeonMode.Tiles;

import my_project.modes.dungeonMode.Dungeon;
import my_project.model.Graphics.Texture;

public class TileDoor extends InteractiveTile{
    public TileDoor(int x, int y, Dungeon dungeon) {
        super(x, y, new Texture("MUSS ICH SPÄTER MACHEN"), dungeon);
    }
    public void interact() {
        setSolid(!isSolid());
    }
}

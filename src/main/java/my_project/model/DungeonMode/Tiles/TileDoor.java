package my_project.model.DungeonMode.Tiles;

import my_project.model.Graphics.Texture;

public class TileDoor extends InteractiveTile{
    public TileDoor(int x, int y) {
        super(x, y, new Texture("MUSS ICH SPÄTER MACHEN"));
    }
    public void interact() {
        setSolid(!isSolid());
    }
}

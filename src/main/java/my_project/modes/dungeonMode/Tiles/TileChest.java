package my_project.modes.dungeonMode.Tiles;

import my_project.model.Graphics.Texture;
import my_project.modes.dungeonMode.Dungeon;

public class TileChest extends InteractiveTile {
    private boolean isClosed = true;
    public TileChest(int x, int y, Dungeon dungeon) {
        super(x, y, new Texture("WallTileSheet.png"), dungeon);
    }

    @Override
    public void interact() {
        if (isClosed){
            isClosed = false;System.out.println("Chest interact");
            dungeon.getPlayer().increaseMoney(10);
        }

    }
}

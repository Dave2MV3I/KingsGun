package my_project.control.modeControl;

import my_project.model.DungeonMode.Dungeon;
import my_project.model.DungeonMode.Tiles.Tile;

public class DungeonModeControl extends ModeControl {
    Dungeon dungeon;
    @Override
    protected void deactivate() {
        dungeon = null;
    }
    @Override
    protected void activate() {
        dungeon = new Dungeon();
    }
    public void setDungeon(Dungeon dungeon){
        this.dungeon = dungeon;
    }
    public Dungeon getDungeon(){
        return dungeon;
    }
    public Tile[][] getDungeonTiles(){
        return dungeon.getTiles();
    }
}

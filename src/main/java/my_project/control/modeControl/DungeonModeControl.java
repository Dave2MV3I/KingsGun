package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.DungeonMode.Dungeon;
import my_project.model.DungeonMode.Monsters.Dragon;
import my_project.model.DungeonMode.Monsters.Monster;
import my_project.model.DungeonMode.Tiles.Tile;

public class DungeonModeControl extends ModeControl {
    Dungeon dungeon;
    public final List<Monster> monsters = new List<>();

    public DungeonModeControl() {

    }

    @Override
    protected void deactivate() {
        dungeon = null;
    }

    @Override
    protected void activate() {
        dungeon = new Dungeon(this);
        if (monsters.isEmpty()) {
            dungeon.instantiateMonsters();
        }
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        dungeon.update(dt);
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

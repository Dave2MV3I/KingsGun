package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.DungeonMode.Dungeon;
import my_project.model.DungeonMode.Monsters.Dragon;
import my_project.model.DungeonMode.Monsters.Monster;
import my_project.model.DungeonMode.Player;
import my_project.model.DungeonMode.Tiles.Tile;

public class DungeonModeControl extends ModeControl {
    Dungeon dungeon;
    Player player;
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
        player = new Player(256,64, this);
        if (monsters.isEmpty()) {
            dungeon.instantiateMonsters();
        }
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        dungeon.update(dt);
        player.update(dt);
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
    public Tile getTile(int x, int y){
        return dungeon.getTile(x, y);
    }
    public Tile getTileByCoord(double x, double y){
        return dungeon.getTileFromCoordinates(x, y);
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player = player;
    }

}

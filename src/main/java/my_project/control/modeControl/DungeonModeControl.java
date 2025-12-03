package my_project.control.modeControl;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.DungeonMode.Dungeon;
import my_project.model.DungeonMode.Monsters.*;
import my_project.model.DungeonMode.Player;
import my_project.model.DungeonMode.Tiles.Tile;
import my_project.view.modeView.DungeonModeView;

public class DungeonModeControl extends ModeControl<DungeonModeView> {
    Dungeon dungeon;
    Player player;
    private final List<Class<? extends Monster>> monsterClasses = new List<>();

    public DungeonModeControl() {
        // Add the classes of possible monsters to the linked list with all monster classes
        monsterClasses.append(Dragon.class);
        monsterClasses.append(Goblin.class);
        monsterClasses.append(Orc.class);
        monsterClasses.append(Dwarf.class);
        monsterClasses.append(Elf.class);
        monsterClasses.append(Ogre.class);
        monsterClasses.append(Troll.class);
    }

    @Override
    protected void deactivate() {
        dungeon = null;
    }

    @Override
    protected void activate() {
        dungeon = new Dungeon(this, monsterClasses);
        player = new Player(256,64, this);
        dungeon.update(0);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        dungeon.update(dt);
        player.update(dt);
        getMonster().update(dt);
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
    public Monster getMonster(){
        return dungeon.getMonster();
    }
}

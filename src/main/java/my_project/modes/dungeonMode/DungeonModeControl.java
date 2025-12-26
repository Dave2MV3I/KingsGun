package my_project.modes.dungeonMode;

import KAGO_framework.model.abitur.datenstrukturen.*;
import my_project.control.MainController;
import my_project.modes.dungeonMode.Monsters.*;
import my_project.modes.dungeonMode.Tiles.*;
import my_project.modes.*;

public class DungeonModeControl extends ModeControl<DungeonModeView> {
    Dungeon dungeon;
    Player player;
    private final List<Class<? extends Monster>> monsterClasses = new List<>();

    public DungeonModeControl(MainController mainController) {
        super(mainController);
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

    }

    @Override
    protected void activate() {
        player = new Player(256,128, this);
        dungeon = new Dungeon(this, monsterClasses);
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

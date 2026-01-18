package my_project.modes.dungeonMode;

import KAGO_framework.model.abitur.datenstrukturen.*;
import my_project.control.MainController;
import my_project.model.Player;
import my_project.modes.dungeonMode.Attacks.AttackRepresentation;
import my_project.modes.dungeonMode.Monsters.*;
import my_project.modes.dungeonMode.Tiles.*;
import my_project.modes.*;

public class DungeonModeControl extends ModeControl<DungeonModeView> {
    Dungeon dungeon;
    DungeonPlayer dungeonPlayer;
    private final List<Class<? extends Monster>> monsterClasses = new List<>();

    public DungeonModeControl(MainController mainController) {
        super(mainController);
        // Add the classes of possible monsters to the linked list with all monster classes
        monsterClasses.append(Troll.class);
        monsterClasses.append(Ogre.class);
        monsterClasses.append(Elf.class);
        monsterClasses.append(Dwarf.class);
        monsterClasses.append(Orc.class);
        monsterClasses.append(Goblin.class);
        monsterClasses.append(Dragon.class);

    }

    @Override
    protected void deactivate() {

    }

    @Override
    protected void activate() {
        dungeonPlayer = new DungeonPlayer(256,128, this);
        dungeon = new Dungeon(this, monsterClasses);
        dungeon.update(0);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        dungeon.update(dt);
        dungeonPlayer.update(dt);
        getMonster().update(dt);
        
        for (AttackRepresentation aR: dungeon.getCurrentAttacks()) {
            aR.update(dt);
        }


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
    public DungeonPlayer getDungeonPlayer(){
        return dungeonPlayer;
    }
    public void setPlayer(DungeonPlayer dungeonPlayer){
        this.dungeonPlayer = dungeonPlayer;
    }
    public Monster getMonster(){
        return dungeon.getMonster();
    }
    public void exit(){
        mainController.loadMode("village", "exit Dungeon");
        mainController.getCurrentPlayer().addMoney(dungeonPlayer.getMoney());
    }
    public void playerDied(){
        mainController.loadMode("start", "death");
    }

    public Player getPlayer() {
        return mainController.getCurrentPlayer();
    }

    public void removeAttack(AttackRepresentation aR){
        dungeon.getCurrentAttacks().remove(aR);
    }
}

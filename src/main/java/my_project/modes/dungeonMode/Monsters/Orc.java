package my_project.modes.dungeonMode.Monsters;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.AttackData;

public class Orc extends Monster{
    public Orc(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.WEIGHT_ATTACK, AttackData.AXE_ATTACK});
        setAnimatedTexture("orc.png", 1, 4);
    }

}

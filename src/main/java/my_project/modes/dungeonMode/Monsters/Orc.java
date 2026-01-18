package my_project.modes.dungeonMode.Monsters;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Attacks.AttackData;

/**
 * A dungeon monster.
 * <br>
 * David Glusmann
 */
public class Orc extends Monster{
    public Orc(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.SWORD_ATTACK, AttackData.AXE_ATTACK, AttackData.DAGGER_ATTACK});
        setAnimatedTexture("orc.png", 1, 4);
    }

}

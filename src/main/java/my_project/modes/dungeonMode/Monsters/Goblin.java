package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Attacks.AttackData;

/**
 * A dungeon monster.
 * <br>
 * David Glusmann
 */
public class Goblin extends Monster{
    public Goblin(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.SPEAR_ATTACK, AttackData.DAGGER_ATTACK});
        setAnimatedTexture("goblin.png", 1, 4);
    }
}

package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Attacks.AttackData;

/**
 * A dungeon monster.
 * <br>
 * David Glusmann
 */
public class Ogre extends Monster{
    public Ogre(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.SWORD_ATTACK, AttackData.DAGGER_ATTACK});
        setAnimatedTexture("Ogre.png", 1, 4);
    }
}

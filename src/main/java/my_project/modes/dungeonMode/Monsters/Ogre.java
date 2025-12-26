package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.Attack;

public class Ogre extends Monster{
    public Ogre(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new Attack[]{Attack.WEIGHT_ATTACK});
    }
}

package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.Attack;

public class Dragon extends Monster{
    public Dragon(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new Attack[]{Attack.FIRE_ATTACK});
    }
}

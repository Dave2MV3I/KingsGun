package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.AttackData;

public class Dragon extends Monster{
    public Dragon(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.FIRE_ATTACK});
        setAnimatedTexture("testSlime.png", 1, 2);
    }
}

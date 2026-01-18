package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Attacks.AttackData;

public class Dragon extends Monster{
    public Dragon(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.FIRE_ATTACK});
        setAnimatedTexture("dragon.png", 1, 4);
    }
}

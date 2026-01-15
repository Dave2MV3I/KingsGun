package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Attacks.AttackData;

public class Goblin extends Monster{
    public Goblin(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.SPEAR_ATTACK});
        setAnimatedTexture("testSlime.png", 1, 2);
    }
}

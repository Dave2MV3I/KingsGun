package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Attacks.AttackData;

public class Troll extends Monster{
    public Troll(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.WEIGHT_ATTACK});
        setAnimatedTexture("testSlime.png", 1, 2);
    }
}

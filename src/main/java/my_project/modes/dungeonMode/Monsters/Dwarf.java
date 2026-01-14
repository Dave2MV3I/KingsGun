package my_project.modes.dungeonMode.Monsters;

import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.Attack;

public class Dwarf extends Monster{
    public Dwarf(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new Attack[]{Attack.SPEAR_ATTACK});
        setAnimatedTexture("testSlime.png", 1, 2);
    }
}

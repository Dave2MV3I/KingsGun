package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Attacks.AttackData;

public class Dwarf extends Monster{
    public Dwarf(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new AttackData[]{AttackData.SPEAR_ATTACK, AttackData.DAGGER_ATTACK});
        setAnimatedTexture("Dwarf.png", 1, 4);
    }
}

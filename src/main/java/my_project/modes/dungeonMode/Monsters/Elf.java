package my_project.modes.dungeonMode.Monsters;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.Attack;


public class Elf extends Monster{
    public Elf(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new Attack[]{Attack.SWORD_ATTACK});
    }
}

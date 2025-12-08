package my_project.Modes.DungeonMode.Monsters;
import my_project.Modes.DungeonMode.Tasks.Attack;


public class Elf extends Monster{
    public Elf() {
        super(new Attack[]{Attack.SWORD_ATTACK});
    }
}

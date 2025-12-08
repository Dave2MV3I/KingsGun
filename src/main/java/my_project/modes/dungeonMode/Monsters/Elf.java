package my_project.modes.dungeonMode.Monsters;
import my_project.modes.dungeonMode.Tasks.Attack;


public class Elf extends Monster{
    public Elf() {
        super(new Attack[]{Attack.SWORD_ATTACK});
    }
}

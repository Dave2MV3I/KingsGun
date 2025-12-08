package my_project.model.DungeonMode.Monsters;
import my_project.model.DungeonMode.Tasks.Attack;


public class Elf extends Monster{
    public Elf() {
        super(new Attack[]{Attack.SWORD_ATTACK});
    }
}

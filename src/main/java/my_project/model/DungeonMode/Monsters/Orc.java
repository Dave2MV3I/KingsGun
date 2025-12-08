package my_project.model.DungeonMode.Monsters;
import my_project.model.DungeonMode.Tasks.Attack;

public class Orc extends Monster{
    public Orc() {
        super(new Attack[]{Attack.WEIGHT_ATTACK, Attack.AXE_ATTACK});
    }
}

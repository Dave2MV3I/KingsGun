package my_project.model.DungeonMode.Monsters;

import my_project.model.DungeonMode.Tasks.Attack;

public class Troll extends Monster{
    public Troll() {
        super(new Attack[]{Attack.WEIGHT_ATTACK});
    }
}

package my_project.Modes.DungeonMode.Monsters;

import my_project.Modes.DungeonMode.Tasks.Attack;

public class Troll extends Monster{
    public Troll() {
        super(new Attack[]{Attack.WEIGHT_ATTACK});
    }
}

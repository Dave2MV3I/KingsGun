package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.Tasks.Attack;

public class Troll extends Monster{
    public Troll() {
        super(new Attack[]{Attack.WEIGHT_ATTACK});
    }
}

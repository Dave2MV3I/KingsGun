package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.Tasks.Attack;

public class Goblin extends Monster{
    public Goblin(){
        super(new Attack[]{Attack.SPEAR_ATTACK});
    }
}

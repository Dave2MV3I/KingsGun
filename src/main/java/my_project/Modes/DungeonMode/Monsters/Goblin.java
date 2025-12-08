package my_project.Modes.DungeonMode.Monsters;

import my_project.Modes.DungeonMode.Tasks.Attack;

public class Goblin extends Monster{
    public Goblin(){
        super(new Attack[]{Attack.SPEAR_ATTACK});
    }
}

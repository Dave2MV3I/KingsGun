package my_project.model.DungeonMode.Monsters;

import my_project.model.DungeonMode.Tasks.Attack;

public class Goblin extends Monster{
    public Goblin(){
        super(new Attack[]{Attack.SPEAR_ATTACK});
    }
}

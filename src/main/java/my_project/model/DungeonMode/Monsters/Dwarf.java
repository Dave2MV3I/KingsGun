package my_project.model.DungeonMode.Monsters;

import my_project.model.DungeonMode.Tasks.Attack;

public class Dwarf extends Monster{
    public Dwarf(){
        super(new Attack[]{Attack.SPEAR_ATTACK});
    }
}

package my_project.Modes.DungeonMode.Monsters;

import my_project.Modes.DungeonMode.Tasks.Attack;

public class Dwarf extends Monster{
    public Dwarf(){
        super(new Attack[]{Attack.SPEAR_ATTACK});
    }
}

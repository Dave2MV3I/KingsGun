package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.Tasks.Attack;

public class Dwarf extends Monster{
    public Dwarf(){
        super(new Attack[]{Attack.SPEAR_ATTACK});
    }
}

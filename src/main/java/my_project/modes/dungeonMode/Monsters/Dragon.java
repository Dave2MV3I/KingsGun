package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.Tasks.Attack;

public class Dragon extends Monster{
    public Dragon(){
        super(new Attack[]{Attack.FIRE_ATTACK});
    }
}

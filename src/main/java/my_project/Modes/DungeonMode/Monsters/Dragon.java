package my_project.Modes.DungeonMode.Monsters;

import my_project.Modes.DungeonMode.Tasks.Attack;

public class Dragon extends Monster{
    public Dragon(){
        super(new Attack[]{Attack.FIRE_ATTACK});
    }
}

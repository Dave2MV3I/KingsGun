package my_project.model.DungeonMode.Monsters;

import my_project.model.DungeonMode.Tasks.Attack;

public class Dragon extends Monster{
    public Dragon(){
        super(new Attack[]{Attack.FIRE_ATTACK});
    }
}

package my_project.model.DungeonMode.Monsters;

import my_project.model.DungeonMode.Tasks.Attack;

public class Ogre extends Monster{
    public Ogre(){
        super(new Attack[]{Attack.WEIGHT_ATTACK});
    }
}

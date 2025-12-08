package my_project.Modes.DungeonMode.Monsters;

import my_project.Modes.DungeonMode.Tasks.Attack;

public class Ogre extends Monster{
    public Ogre(){
        super(new Attack[]{Attack.WEIGHT_ATTACK});
    }
}

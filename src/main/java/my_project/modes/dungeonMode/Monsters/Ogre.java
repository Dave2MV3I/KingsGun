package my_project.modes.dungeonMode.Monsters;

import my_project.modes.dungeonMode.Tasks.Attack;

public class Ogre extends Monster{
    public Ogre(){
        super(new Attack[]{Attack.WEIGHT_ATTACK});
    }
}

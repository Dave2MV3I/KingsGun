package my_project.model.DungeonMode.Monsters;

import my_project.model.DungeonMode.Tasks.Attack;

import static my_project.model.DungeonMode.Tasks.Attack.WEIGHT_ATTACK;

public class Goblin extends Monster{
    public Goblin(){
        super();
        Attack[] myAttacks = new Attack[4];
        myAttacks[1] = Attack.AXE_ATTACK;
        Attack[] losAttacks = {WEIGHT_ATTACK};
    }
}

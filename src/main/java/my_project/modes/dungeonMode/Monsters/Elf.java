package my_project.modes.dungeonMode.Monsters;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.Attack;


public class Elf extends Monster{
    public Elf(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new Attack[]{Attack.SWORD_ATTACK});
        setAnimatedTexture("testSlime.png", 1, 2);
    }
}

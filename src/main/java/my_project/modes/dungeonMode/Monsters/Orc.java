package my_project.modes.dungeonMode.Monsters;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.Tasks.Attack;

public class Orc extends Monster{
    public Orc(DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl, new Attack[]{Attack.WEIGHT_ATTACK, Attack.AXE_ATTACK});
        setAnimatedTexture("orc.png", 1, 4);
    }

}

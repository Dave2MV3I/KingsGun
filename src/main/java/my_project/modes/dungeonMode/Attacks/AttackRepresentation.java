package my_project.modes.dungeonMode.Attacks;

import KAGO_framework.view.DrawTool;
import my_project.model.Graphics.Texture;
import my_project.modes.dungeonMode.DungeonEntity;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.DungeonPlayer;

/**
 * Attack representations are moving textures for monster attacks, e.g. fire <br>
 * Note: MVC is broken bcs of practical reasons: V takes data from M without C
 */
public class AttackRepresentation extends DungeonEntity {
    AttackData attackData;
    DungeonPlayer dungeonPlayer;

    public AttackRepresentation(DungeonModeControl dungeonModeControl, AttackData attackData, DungeonPlayer player){
        super(dungeonModeControl);
        this.attackData = attackData;
        this.dungeonPlayer = dungeonPlayer;
    }

    @Override
    public void update(double dt){
        double d = getDirection(dungeonPlayer);
        setVelocityAS(d, attackData.getStrength());
    }

    @Override
    public void draw(DrawTool drawTool){
        texture = new Texture(attackData.getTexture());
    }
}

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
        // TODO
        switch(attackData.ANIMATION_TYPE){
            case MOVING:
                double d = getDirection(dungeonPlayer);
                setVelocityAS(d, attackData.getSTRENGTH());
                // TODO if collision mit Player dann löschen aus Arraylist
            break;
            case STATIC:
                // TODO after 2 seks löschen aus ArrayList
            break;
        }

    }

    @Override
    public void draw(DrawTool drawTool){
        texture = new Texture(attackData.getTEXTURE());
    }
}

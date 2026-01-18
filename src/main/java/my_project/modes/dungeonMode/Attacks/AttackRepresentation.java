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

    public AttackRepresentation(DungeonModeControl dungeonModeControl, AttackData attackData, DungeonPlayer player, double x, double y) {

        super(dungeonModeControl);
        this.x = x;
        this.y = y;
        this.attackData = attackData;
        this.dungeonPlayer = player;
        if (dungeonPlayer != null && attackData.ANIMATION_TYPE.equals(AttackData.AnimationType.MOVING)){
            System.out.println("helpmeeeeeeee");
            double d = getDirection(dungeonPlayer);
            setVelocityAS(d, attackData.getSTRENGTH());
        }
    }

    @Override
    public void update(double dt){
        switch(attackData.ANIMATION_TYPE){
            case MOVING:
                if (collidesWith(dungeonPlayer) || !(movementCondition())) control.removeAttack(this);
                // TODO if collision mit Player dann löschen aus Arraylist
            break;
            case STATIC:
                // TODO after 2 seks löschen aus ArrayList
            break;
        }
    super.update(dt);
    }

    @Override
    public void draw(DrawTool drawTool){
        texture = new Texture(attackData.getTEXTURE());
        texture.autoDraw(drawTool, x, y);
    }
}

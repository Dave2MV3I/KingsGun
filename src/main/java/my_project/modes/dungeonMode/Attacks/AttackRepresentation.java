package my_project.modes.dungeonMode.Attacks;

import KAGO_framework.view.DrawTool;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.dungeonMode.DungeonEntity;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.DungeonPlayer;
import my_project.modes.dungeonMode.Monsters.Monster;

/**
 * Attack representations are moving textures for monster attacks, e.g. fire <br>
 * Note: MVC is broken bcs of practical reasons: V takes data from M without C
 */
public class AttackRepresentation extends DungeonEntity {
    AttackData attackData;
    DungeonPlayer dungeonPlayer;
    double damage;
    double attackCoolDown = 2;
    Monster myMonster;

    public AttackRepresentation(DungeonModeControl dungeonModeControl, AttackData attackData, DungeonPlayer player, double damage, double x, double y) {
        super(dungeonModeControl);
        this.attackData = attackData;
        this.x = x;
        this.y = y;

        this.dungeonPlayer = player;
        this.damage = damage;

        if (dungeonPlayer != null && attackData.ANIMATION_TYPE.equals(AttackData.AnimationType.MOVING)){
            double d = getDirection(dungeonPlayer);
            setVelocityAS(d, attackData.STRENGTH);
        }

        switch(attackData.ANIMATION_TYPE){
            case MOVING:
                texture = new AnimatedSpriteSheet(attackData.TEXTURE, 1, 4);
                break;
            case STATIC:
                texture = new AnimatedSpriteSheet(attackData.TEXTURE, 1, 8);
                radius = 16;
                break;
        }
    }

    @Override
    public void update(double dt){
        if (collidesWith(dungeonPlayer)) dungeonPlayer.damage(damage);
        if (attackData.ANIMATION_TYPE.equals(AttackData.AnimationType.STATIC)){
                x = myMonster.getX();
                y = myMonster.getY();
                attackCoolDown -= dt;
                if (attackCoolDown <= 0){control.removeAttack(this);}
        }
        texture.update(dt);
        super.update(dt);
    }

    @Override
    public void onCollision(){control.removeAttack(this);}

    @Override
    public void draw(DrawTool drawTool){
        ((AnimatedSpriteSheet)texture).setFrameCooldownX(0.2);
        autoDrawHitbox(drawTool);
        texture.autoDraw(drawTool, x-radius, y-radius);
    }

    public void setMonster(Monster m){this.myMonster = m;}
}

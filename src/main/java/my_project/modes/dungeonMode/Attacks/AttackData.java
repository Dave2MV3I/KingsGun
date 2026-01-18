package my_project.modes.dungeonMode.Attacks;

/**
 * AttackData stores model information for each monster attack.
 */
public enum AttackData {
    DAGGER_ATTACK(5, 10, "dagger.png", 50, AnimationType.MOVING),
    AXE_ATTACK(25, 50, "axe.png", AnimationType.STATIC),
    SPEAR_ATTACK(10, 50, "spear.png", 50, AnimationType.MOVING),
    SWORD_ATTACK(40, 60, "sword.png", AnimationType.STATIC),
    FIRE_ATTACK(70, 90, "fire.png", AnimationType.STATIC);

    public final double MIN_DAMAGE;
    public final double MAX_DAMAGE;
    public final String TEXTURE;
    public final double STRENGTH;
    public final AnimationType ANIMATION_TYPE;

    public enum AnimationType{
        MOVING, STATIC;
    }

    AttackData(double minDamage, double maxDamage, String texture, double strength, AnimationType animationType) {
        this.MIN_DAMAGE = minDamage;
        this.MAX_DAMAGE = maxDamage;
        this.TEXTURE = texture;
        this.STRENGTH = strength;
        this.ANIMATION_TYPE = animationType;
    }

    AttackData(double minDamage, double maxDamage, String texture, AnimationType animationType){
        this.MIN_DAMAGE = minDamage;
        this.MAX_DAMAGE = maxDamage;
        this.TEXTURE = texture;
        this.STRENGTH = 0;
        this.ANIMATION_TYPE = animationType;
    }

    public double calculateDamage(){
        return MIN_DAMAGE + Math.random()*(MAX_DAMAGE - MIN_DAMAGE);
    }

}

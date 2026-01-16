package my_project.modes.dungeonMode.Attacks;

/**
 * AttackData stores model information for each monster attack.
 */
public enum AttackData {
    WEIGHT_ATTACK(50, 75, "dagger.png", 10, AnimationType.MOVING),
    AXE_ATTACK(25, 50, "dagger.png", 10, AnimationType.MOVING),
    SPEAR_ATTACK(10, 50, "dagger.png", 10, AnimationType.MOVING),
    SWORD_ATTACK(10, 20, "dagger.png", 10, AnimationType.MOVING, 1),
    FIRE_ATTACK(70, 90, "dagger.png", 10, AnimationType.STATIC, 0.8);

    public final double MIN_DAMAGE;
    public final double MAX_DAMAGE;
    public final double ATTACK_FREQUENCY;
    public final String TEXTURE;
    public final double STRENGTH;
    public final AnimationType ANIMATION_TYPE;

    public enum AnimationType{
        MOVING, STATIC;
    }

    AttackData(double minDamage, double maxDamage, String texture, double strength, AnimationType animationType) {
        this.MIN_DAMAGE = minDamage;
        this.MAX_DAMAGE = maxDamage;
        this.ATTACK_FREQUENCY = 0.5;
        this.TEXTURE = texture;
        this.STRENGTH = strength;
        this.ANIMATION_TYPE = animationType;
    }

    AttackData(double minDamage, double maxDamage, String texture, double strength, AnimationType animationType, double attackFrequency){
        this.MIN_DAMAGE = minDamage;
        this.MAX_DAMAGE = maxDamage;
        this.TEXTURE = texture;
        this.STRENGTH = strength;
        this.ANIMATION_TYPE = animationType;
        this.ATTACK_FREQUENCY = attackFrequency;
    }

    public double calculateDamage(){
        return MIN_DAMAGE + Math.random()*(MAX_DAMAGE - MIN_DAMAGE);
    }
    public String getTEXTURE(){return TEXTURE;}
    public double getSTRENGTH(){return STRENGTH;}

}
// TODO (IDEE): Je länger man einen Button hält (Waffe aufladen) oder je schneller man sich bewegt, desto mehr Schaden

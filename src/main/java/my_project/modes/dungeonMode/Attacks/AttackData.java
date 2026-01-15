package my_project.modes.dungeonMode.Attacks;

public enum AttackData {
    WEIGHT_ATTACK(50, 75, "dagger.png", 10),
    AXE_ATTACK(25, 50, "dagger.png", 10),
    SPEAR_ATTACK(10, 50, "dagger.png", 10),
    SWORD_ATTACK(10, 20, "dagger.png", 10,1),
    FIRE_ATTACK(70, 90, "dagger.png", 10, 0.8);

    private final double minDamage;
    private final double maxDamage;
    private final double attackFrequency;
    private final String texture;
    private final double strength;

    AttackData(double minDamage, double maxDamage, String texture, double strength) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.attackFrequency = 0.5;
        this.texture = texture;
        this.strength = strength;
    }

    AttackData(double minDamage, double maxDamage, String texture, double strength, double attackFrequency){
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.texture = texture;
        this.strength = strength;
        this.attackFrequency = attackFrequency;
    }

    public double calculateDamage(){
        return minDamage + Math.random()*(maxDamage-minDamage);
    }
    public String getTexture(){return texture;}
    public double getStrength(){return strength;}
}
// TODO (IDEE): Je länger man einen Button hält (Waffe aufladen) oder je schneller man sich bewegt, desto mehr Schaden

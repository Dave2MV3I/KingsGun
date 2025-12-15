package my_project.modes.dungeonMode.Tasks;

public enum Attack{
    WEIGHT_ATTACK(50, 75),
    AXE_ATTACK(25, 50),
    SPEAR_ATTACK(10, 50),
    SWORD_ATTACK(10, 20, 1),
    FIRE_ATTACK(70, 90, 0.8);

    private final double minDamage;
    private final double maxDamage;
    private final double attackSpeed;

    Attack(double minDamage, double maxDamage){
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.attackSpeed = 0.5;
    }

    Attack(double minDamage, double maxDamage, double attackSpeed){
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.attackSpeed = attackSpeed;
    }

    public double calculateDamage(){
        return minDamage + Math.random()*(maxDamage-minDamage);
    }
}
// TODO (IDEE): Je länger man einen Button hält (Waffe aufladen) oder je schneller man sich bewegt, desto mehr Schaden

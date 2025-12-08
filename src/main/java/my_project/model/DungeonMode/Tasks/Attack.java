package my_project.model.DungeonMode.Tasks;

public enum Attack {
    WEIGHT_ATTACK{
        double minDamage = 50;
        double maxDamage = 75;
    },
    AXE_ATTACK{
        double minDamage = 25;
        double maxDamage = 50;
    },
    SPEAR_ATTACK{ // TODO (IDEE): Je länger man einen Button hält (Waffe aufladen) oder je schneller man sich bewegt, desto mehr Schaden
        double minDamage = 10;
        double maxDamage = 50;
    },
    SWORD_ATTACK{
        double minDamage = 10;
        double maxDamage = 20;
        double attackSpeed = 1;
    },
    FIRE_ATTACK{
        double minDamage = 70;
        double maxDamage = 90;
        double attackSpeed = 0.8;
    };

    private double minDamage;
    private double maxDamage;
    private double attackSpeed = 0.5;

    public double calculateDamage(){
        return minDamage + Math.random()*(maxDamage-minDamage);
    }
}


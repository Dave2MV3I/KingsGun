package my_project.modes.dungeonMode.Monsters;

import KAGO_framework.model.abitur.datenstrukturen.Stack;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.model.Player;
import my_project.modes.dungeonMode.DungeonEntity;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.DungeonPlayer;
import my_project.modes.dungeonMode.PathTile;
import my_project.modes.dungeonMode.Tasks.Attack;
import my_project.model.Graphics.AnimatedSpriteSheet;

import java.awt.*;

/**
 * In each Dungeon spawns some type of Monster.
 * <br><br>
 * David Glusmann
 */

public abstract class Monster extends DungeonEntity {
    protected Attack[] myAttacks;
    protected DungeonPlayer dungeonPlayer;
    private double coolDown = 2;

    public Monster(DungeonModeControl dungeonModeControl, Attack[] attacks){
        super(dungeonModeControl);
        myAttacks = attacks;

        radius = 16;
        texture = new AnimatedSpriteSheet("testSlime.png", 1, 2);
        ((AnimatedSpriteSheet)texture).setFrameCooldownX(0.5);

        this.dungeonPlayer = dungeonModeControl.getDungeonPlayer();
        //TODO David: Design the monsters with different appearances and get them to know their tasks in the enum Task with all tasks
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.drawFilledRectangle(this.x, this.y, 10, 10);
        drawTool.drawFilledRectangle(this.x+5, this.y+5, 10, 10);
        texture.autoDraw(drawTool, x-radius, y-radius, 32);
        drawTool.setCurrentColor(new Color(255, 0, 0));
        autoDrawHitbox(drawTool);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        texture.update(dt);
        coolDown -= dt;
        if(getDistanceTo(dungeonPlayer) <= 32 && coolDown <= 0) this.attack();
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    private void attack(){
        int numAttacks = myAttacks.length;
        int rndAttack = (int)(Math.random()*numAttacks);
        Attack attack = myAttacks[rndAttack];
        double damage = attack.calculateDamage();
        //System.out.println(damage + " damage");
        dungeonPlayer.damage(damage);
        System.out.println(dungeonPlayer.getHealth());
        coolDown = 2;

        // TODO Queue machen für Aktivitäten wie gehen und angreifen (Methode attack sollte im enum sein, nicht hier)
        // TODO Drache schwer zu kriegen machen
    }

    private void findPath(){
        PathTile start = new PathTile(control.getDungeon().getTileFromCoordinates(x,y));
        DungeonPlayer player = control.getDungeonPlayer();
        PathTile dest = new PathTile(control.getDungeon().getTileFromCoordinates(player.getX(),player.getY()));
        List<PathTile> pending = new List<>();
        Stack<PathTile> visited = new Stack<>();

        pending.append(start);
        // TODO Joshi: Die Tiles sollen ihre Position im tilesArray oder ihre 8 Nachbarn kennen,
        //  damit der Path gefunden werden kann, ohne dass jeder Tile mit einer Schleife im Array anhand seiner Koordinaten gesucht werden muss
    }
}

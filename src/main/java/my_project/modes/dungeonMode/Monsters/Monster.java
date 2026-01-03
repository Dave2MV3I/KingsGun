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
import my_project.modes.dungeonMode.Tiles.Tile;
import my_project.modes.dungeonMode.Tiles.TileWall;

import java.awt.*;

/**
 * In each Dungeon spawn some type of Monster.
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

    /**
     * Monsters call this method in order to get the shortest path to the player.
     */
    private void findPath(){
        DungeonPlayer player = control.getDungeonPlayer();
        Tile destTile = control.getDungeon().getTileFromCoordinates(player.getX(),player.getY());
        Tile startTile = control.getDungeon().getTileFromCoordinates(x,y);
        PathTile dest = new PathTile(destTile, null, destTile);
        PathTile start = new PathTile(startTile, null, dest.getTile());
        PathTile current = start;

        List<PathTile> pending = new List<>();
        Stack<PathTile> visited = new Stack<>();

        pending.append(start);
        current = start;

        if (current != dest){
            // Nur pathTile erstellen, wenn zu diesem tile noch nicht erstellt
            Tile up = current.getTile().getRelative("up");
            Tile upRight = current.getTile().getRelative("upRight");
            Tile right = current.getTile().getRelative("right");
            Tile downRight = current.getTile().getRelative("downRight");
            Tile down = current.getTile().getRelative("down");
            Tile downLeft = current.getTile().getRelative("downLeft");
            Tile left = current.getTile().getRelative("left");
            Tile upLeft = current.getTile().getRelative("upLeft");

            // Create PathTile for each tile which isn't a wall and insert it into pending ordered by cost
            if (!(up instanceof TileWall)){
                PathTile upPT= new PathTile(up, current, dest.getTile());
                pending.toFirst();
                instertOrderingCost(pending, upPT);
            }
            if (!(upRight instanceof TileWall)){
                PathTile upRightPT= new PathTile(upRight, current, dest.getTile());
                instertOrderingCost(pending, upRightPT);
            }
            if (!(right instanceof TileWall)){
                PathTile rightPT= new PathTile(right, current, dest.getTile());
                instertOrderingCost(pending, rightPT);
            }
            if (!(downRight instanceof TileWall)){
                PathTile downRightPT= new PathTile(downRight, current, dest.getTile());
                instertOrderingCost(pending, downRightPT);
            }
            if (!(down instanceof TileWall)){
                PathTile downPT= new PathTile(down, current, dest.getTile());
                instertOrderingCost(pending, downPT);
            }
            if (!(downLeft instanceof TileWall)){
                PathTile downLeftPT= new PathTile(downLeft, current, dest.getTile());
                instertOrderingCost(pending, downLeftPT);
            }
            if (!(left instanceof TileWall)){
                PathTile leftPT= new PathTile(left, current, dest.getTile());
                instertOrderingCost(pending, leftPT);
            }
            if (!(upLeft instanceof TileWall)){
                PathTile upLeftPT= new PathTile(upLeft, current, dest.getTile());
                instertOrderingCost(pending, upLeftPT);
            }

            // move current to visited, because all its neighbours which aren't wall are inside of pending
            visited.push(current);
            pending.remove();

            // highest PathTile, the one with the lowest cost, gets checked out
            pending.toFirst();
            current = pending.getContent();

            // TODO The above needs to be repeated, until current is dest
            // TODO Monster kann in sackgasse geraten, deshalb muss es auch zurückgehen können und:
            //  Weg zu einem Tile und sein cost können sich ändern, wenn man einen kürzeren Weg zum Tile findet.
        } else {
            // TODO When destination reached, all tiles from the path are put into a stack, the monster knows.
            //  The monster then calls a method which changes its direction after each tile so that it can follow the path
            //  The path is updated, meaning that this method findPath() is called every 2 seconds (cooldown)
        };

    }

    private void instertOrderingCost(List<PathTile> list, PathTile pT){
        while (list.hasAccess() && list.getContent().getCost() < pT.getCost()){
            list.next();
            if (!list.hasAccess()){
                list.append(pT);
            } else list.insert(pT);
        }
    }
}

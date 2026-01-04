package my_project.modes.dungeonMode.Monsters;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.modes.dungeonMode.DungeonEntity;
import my_project.modes.dungeonMode.DungeonModeControl;
import my_project.modes.dungeonMode.DungeonPlayer;
import my_project.modes.dungeonMode.PathNode;
import my_project.modes.dungeonMode.Tasks.Attack;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.dungeonMode.Tiles.Tile;

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

    /*
    *//**
     * Monsters call this method in order to get the shortest path to the player.
     *//*
    private void findPath(){
        DungeonPlayer player = control.getDungeonPlayer();
        Tile destTile = control.getDungeon().getTileFromCoordinates(player.getX(),player.getY());
        Tile startTile = control.getDungeon().getTileFromCoordinates(x,y);
        PathNode dest = new PathNode(destTile, null, destTile);
        PathNode start = new PathNode(startTile, null, dest.getTile());
        PathNode current = start;

        List<PathNode> pending = new List<>();
        Stack<PathNode> visited = new Stack<>();

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

            // Create PathNode for each tile which isn't a wall and insert it into pending ordered by cost
            if (!(up instanceof TileWall)){
                PathNode upPT= new PathNode(up, current, dest.getTile());
                pending.toFirst();
                instertOrderingCost(pending, upPT);
            }
            if (!(upRight instanceof TileWall)){
                PathNode upRightPT= new PathNode(upRight, current, dest.getTile());
                instertOrderingCost(pending, upRightPT);
            }
            if (!(right instanceof TileWall)){
                PathNode rightPT= new PathNode(right, current, dest.getTile());
                instertOrderingCost(pending, rightPT);
            }
            if (!(downRight instanceof TileWall)){
                PathNode downRightPT= new PathNode(downRight, current, dest.getTile());
                instertOrderingCost(pending, downRightPT);
            }
            if (!(down instanceof TileWall)){
                PathNode downPT= new PathNode(down, current, dest.getTile());
                instertOrderingCost(pending, downPT);
            }
            if (!(downLeft instanceof TileWall)){
                PathNode downLeftPT= new PathNode(downLeft, current, dest.getTile());
                instertOrderingCost(pending, downLeftPT);
            }
            if (!(left instanceof TileWall)){
                PathNode leftPT= new PathNode(left, current, dest.getTile());
                instertOrderingCost(pending, leftPT);
            }
            if (!(upLeft instanceof TileWall)){
                PathNode upLeftPT= new PathNode(upLeft, current, dest.getTile());
                instertOrderingCost(pending, upLeftPT);
            }

            // move current to visited, because all its neighbours which aren't wall are inside of pending
            visited.push(current);
            pending.remove();

            // highest PathNode, the one with the lowest cost, gets checked out
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

    /**
     * Monsters call this method in order to get the shortest possible path to tht player.
     * @param start The tile the monster starts at.
     * @param goal The monsters destination tile, the players position.
     * @return an array of tiles the monster will follow
     */
    private Tile[] findPath(Tile start, Tile goal){
        List<PathNode> openList = new List<>();
        List<PathNode> closedList = new List<>();

        Tile startTile = control.getDungeon().getTileFromCoordinates(x,y);
        Tile goalTile = control.getDungeon().getTileFromCoordinates(control.getDungeonPlayer().getX(), control.getDungeonPlayer().getY());

        PathNode startNode = new PathNode(startTile, goalTile);
        openList.append(startNode);
        startNode.setDistance(0);
        startNode.calculateCost();
        startNode.setParent(null);

        while (!openList.isEmpty()){
            // Node with least cost is going to be examined, is the new current
            openList.toFirst();
            PathNode current = openList.getContent();

            // If node is goalNode, return found path
            if (current.getTile() == goalTile) return reconstructPath(current);

            closedList.append(current);
            openList.remove();

            Tile[] neighbors = current.getNotWallNeighboringTiles();
            outer:
            for (Tile neighbor : neighbors){

                // Check if pathNode is already closed, meaning that its evaluated (and its neighbors in openList)
                closedList.toFirst();
                while (closedList.hasAccess()){
                    if (closedList.getContent().getTile() == neighbor) continue outer; // Jumps to next tile bcs this one already in closedList
                }

                double tentativeDistance = current.getDistance() + current.getTile().getDistanceTo(neighbor);

                // Check if pathNode already visited, meaning if inside openList
                openList.toFirst();
                boolean insideOpenList = false;
                while (openList.hasAccess()){
                    if (openList.getContent().getTile() == neighbor) insideOpenList = true;
                }

                PathNode neighborNode = new PathNode(neighbor, goalTile);
                if (!insideOpenList){
                    openList.append(neighborNode);
                } else if (tentativeDistance >= neighborNode.getDistance()) continue; // This path is not shorter

                // Calculate neighboring pathTiles values or update them, if its parent is set to current bcs then path to this pathTile is shorter
                neighborNode.setDistance(tentativeDistance);
                neighborNode.setParent(current);
                neighborNode.calculateCost();
            }
        }
        return null; // No path found
    }

    /**
     * Inserts a pathTile into the openList on the correct position by its cost
     * @param list the openList
     * @param pathTile the pathTile to be inserted into the openList
     */
    private void insertByCost(List<PathNode> list, PathNode pathTile){
        while (list.hasAccess() && list.getContent().getCost() < pathTile.getCost()){
            list.next();
        }
        if (!list.hasAccess()){
            list.append(pathTile);
        } else list.insert(pathTile);
    }

    private Tile[] reconstructPath(PathNode goalNode){
        int nodes = 1;
        while (goalNode.getParent() != null){
            goalNode = goalNode.getParent();
            nodes++;
        }

        Tile[] path = new Tile[nodes];
        PathNode current = goalNode;

        for (int i = 0; i < nodes; i++) {
            path[i] = current.getTile();
            current = current.getParent();
        }
        return path;
    }




}

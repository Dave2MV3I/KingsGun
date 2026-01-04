package my_project.modes.dungeonMode.Monsters;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
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
    private double attackCoolDown = 2;
    private double pathCoolDown = 2;
    private Queue<Tile> currentPath = new Queue<>();

    public Monster(DungeonModeControl dungeonModeControl, Attack[] attacks){
        super(dungeonModeControl);
        myAttacks = attacks;

        radius = 10;
        texture = new AnimatedSpriteSheet("testSlime.png", 1, 2);
        ((AnimatedSpriteSheet)texture).setFrameCooldownX(0.5);

        this.dungeonPlayer = dungeonModeControl.getDungeonPlayer();
        //TODO David: Design the monsters with different appearances and get them to know their tasks in the enum Task with all tasks
    }

    @Override
    public void draw(DrawTool drawTool){
        drawTool.drawFilledRectangle(this.x, this.y, 10, 10);
        drawTool.drawFilledRectangle(this.x+5, this.y+5, 10, 10);
        texture.autoDraw(drawTool, x-radius, y-radius, 20); // TODO give the monsters their textures
        drawTool.setCurrentColor(new Color(255, 0, 0));
        autoDrawHitbox(drawTool);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        texture.update(dt);

        // Attack player every 2 seconds if near enough
        attackCoolDown -= dt;
        if(getDistanceTo(dungeonPlayer) <= 32 && attackCoolDown <= 0) this.attack();

        // Update path every 2 seconds
        pathCoolDown -= dt;
        if (pathCoolDown <= 0) {
            currentPath = findPath();
            pathCoolDown = 2;
        }

        // Update direction according to the path
        if (!currentPath.isEmpty() && !this.collidesWith(currentPath.front())) {
            currentPath.dequeue();
            setVelocityAS(getDirection(currentPath.front()), 50); // TODO every monster has its own speed
        }
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    private void attack(){
        // TODO animate attacks
        int numAttacks = myAttacks.length;
        int rndAttack = (int)(Math.random()*numAttacks);
        Attack attack = myAttacks[rndAttack];
        double damage = attack.calculateDamage();
        //System.out.println(damage + " damage");
        dungeonPlayer.damage(damage);
        System.out.println(dungeonPlayer.getHealth());
        attackCoolDown = 2;
    }

    /**
     * Monsters call this method in order to get the shortest possible path to tht player.
     * @return an array of tiles, the updated path, the monster follows for 2 seconds
     */
    private Queue<Tile> findPath(){
        List<PathNode> openList = new List<>();
        List<PathNode> closedList = new List<>();

        Tile start = control.getDungeon().getTileFromCoordinates(x,y);
        Tile goal = control.getDungeon().getTileFromCoordinates(control.getDungeonPlayer().getX(), control.getDungeonPlayer().getY());

        PathNode startNode = new PathNode(start, goal);
        openList.append(startNode);
        startNode.setDistance(0);
        startNode.calculateCost();
        startNode.setParent(null);

        while (!openList.isEmpty()){
            // Node with least cost is going to be examined, is the new current
            openList.toFirst();
            PathNode current = openList.getContent();

            // If node is goalNode, return found path
            if (current.getTile() == goal) {
                System.out.println("Path updated");
                return reconstructPath(current);
            }

            closedList.append(current);
            openList.remove();

            Tile[] neighbors = current.getNotWallNeighboringTiles();
            outer:
            for (Tile neighbor : neighbors){

                // Check if pathNode is already closed, meaning that its evaluated (and its neighbors in openList)
                closedList.toFirst();
                while (closedList.hasAccess()){
                    if (closedList.getContent().getTile() == neighbor) continue outer; // Jumps to next tile bcs this one already in closedList
                    closedList.next();
                }

                double tentativeDistance = current.getDistance() + current.getTile().getDistanceTo(neighbor);

                // Check if pathNode already visited, meaning if inside openList
                openList.toFirst();
                boolean insideOpenList = false;
                while (openList.hasAccess()){
                    if (openList.getContent().getTile() == neighbor) insideOpenList = true;
                    openList.next();
                }

                PathNode neighborNode = new PathNode(neighbor, goal);
                if (!insideOpenList){
                    insertByCost(openList, neighborNode);
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
     * @param pathNode the pathNode to be inserted into the openList
     */
    private void insertByCost(List<PathNode> list, PathNode pathNode){
        list.toFirst();
        while (list.hasAccess() && list.getContent().getCost() < pathNode.getCost()){
            list.next();
        }
        if (!list.hasAccess()){
            list.append(pathNode);
        } else list.insert(pathNode);
    }

    private Queue<Tile> reconstructPath(PathNode goalNode){
        Queue<Tile> path = new Queue<>();
        PathNode current = goalNode;

        while (current.getParent() != null){
            path.enqueue(current.getTile());
            current = current.getParent();
        }
         return path;
    }




}

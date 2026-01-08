package my_project.modes.dungeonMode;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.modes.dungeonMode.Tiles.Tile;
import my_project.modes.dungeonMode.Tiles.TileWall;

/**
 * A PathNode is a possible node in the path to the monsters destination.
 * This wrapper class saves one Tile with added information needed for the path finding algorithm to work.
 */
public class PathNode {
    private PathNode parent;
    private Tile tile;
    private double heuristic;
    private double distance;
    private double cost;

    public PathNode(Tile t, Tile goalTile) {
        this.tile = t;
        heuristic = tile.getDistanceTo(goalTile);
    }

    /**
     *
     * @return the tile which belongs to the pathTile
     */
    public Tile getTile() {return tile;}

    /**
     *
     * @param t sets the previous pathTile in the path which leads to this one
     */
    public void setParent(PathNode t) {this.parent = t;}
    public PathNode getParent() {return parent;}

    /**
     *
     * @param d the distance from the paths start to this pathTile
     */
    public void setDistance(double d) {distance = d;}
    public double getDistance() {return distance;}

    /**
     * Calculates the cost of going over this PT. distance from start to this PT + estimated distance from this PT to the destination
     */
    public void calculateCost() {cost = distance + heuristic;}
    public double getCost() {return cost;}

    public Tile[] getNotWallNeighboringTiles() {
        Queue<Tile> queue = new Queue<Tile>();
        Stack<Tile> stack = new Stack<Tile>();

        Tile up = tile.getRelative("up");
        if (!(up.isSolid())) queue.enqueue(up);
        /*
        Tile upRight = tile.getRelative("upRight");
        if (!(upRight.isSolid())) queue.enqueue(upRight);
        */
        Tile right = tile.getRelative("right");
        if (!(right.isSolid())) queue.enqueue(right);
        /*
        Tile downRight = tile.getRelative("downRight");
        if (!(downRight.isSolid())) queue.enqueue(downRight);
        */
        Tile down = tile.getRelative("down");
        if (!(down.isSolid())) queue.enqueue(down);
        /*
        Tile downLeft = tile.getRelative("downLeft");
        if (!(downLeft.isSolid())) queue.enqueue(downLeft);
        */
        Tile left = tile.getRelative("left");
        if (!(left.isSolid())) queue.enqueue(left);
        /*
        Tile upLeft = tile.getRelative("upLeft");
        if (!(upLeft.isSolid())) queue.enqueue(upLeft);
        */
        int tiles = 0;
        while (!queue.isEmpty()) {
            stack.push(queue.front());
            queue.dequeue();
            tiles ++;
        }
        while(!stack.isEmpty()){
            queue.enqueue(stack.top());
            stack.pop();
        }

        Tile[] neighbors = new Tile[tiles];
        for (int i = 0; i < tiles; i++) {
            neighbors[i] = queue.front();
            queue.dequeue();
        }
        return neighbors;
    }

}

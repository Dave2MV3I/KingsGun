package my_project.modes.dungeonMode;
import my_project.modes.dungeonMode.Tiles.Tile;

/**
 * A PathTile is a Tile with added information needed for the path finding algorithm of monsters.
 * previous -> previous tile in the path to the monsters destination
 * distance -> the distance to the monsters destination
 */
public class PathTile {
    private PathTile previous;
    private Tile tile;
    private double heuristic;
    private double distance;
    private double cost;

    public PathTile(Tile t, PathTile previous, Tile dest) {
        this.tile = t;
        this.previous = previous;
        this.distance = previous.getDistance() + t.getDistanceTo(previous.getTile());
        heuristic = t.getDistanceTo(dest);
        calculateCost();
    }

    public void setPrevious(PathTile t) {this.previous = t;}
    public PathTile getPrevious() {return previous;}

    public Tile getTile() {return tile;}

    public void calculateCost() {cost = distance + heuristic;}
    public double getCost() {return cost;}

    public void setDistance(double d) {distance = d;}
    public double getDistance() {return distance;}
}

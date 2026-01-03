package my_project.modes.dungeonMode;
import my_project.modes.dungeonMode.Tiles.Tile;

/**
 * A PathTile is a Tile with added information needed for the path finding algorithm of monsters.
 * previous -> previous tile in the path to the monsters destination
 * distance -> the distance to the monsters destination
 */
public class PathTile {
    private Tile previous;
    private Tile tile;
    private double heuristic;
    private double distanceToTile;

    public PathTile(Tile t) {
        this.tile = t;
        //heuristic = ; distance to dest.
    }

    public void setPrevious(Tile t) {this.previous = t;}
    public Tile getPrevious() {return previous;}

    public void setDistanceToTile(double d) {this.distanceToTile = d;}
    public double getDistanceToTile() {return distanceToTile;}
}

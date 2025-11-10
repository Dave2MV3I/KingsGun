package my_project.model.DungeonMode;

public abstract class Tile {
    private static final double WIDTH = 16;
    private static final double HEIGHT = 16;

    public Tile(int x, int y) {

    }
    public static double getWidth() {
        return WIDTH;
    }
    public static double getHEIGHT() {
        return HEIGHT;
    }
}

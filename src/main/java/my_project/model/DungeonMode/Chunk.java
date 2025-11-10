package my_project.model.DungeonMode;

public class Chunk {
    private Tile[][] tiles; // [x][y]
    public Tile[][] getTiles() {
        return tiles;
    }
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
    public void setTile(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }
}

package my_project.modes.dungeonMode.Tiles;

import my_project.modes.dungeonMode.Dungeon;
import my_project.model.Graphics.TileSheet;
/** Grid Based Tile for the Dungeon, to stop the Player
 * By joshua Becker
 */
public class TileWall extends Tile {
    public TileWall(int x, int y, Dungeon dungeon) {
        super(x, y, new TileSheet("WallTileSheet32x32T.png"), dungeon);
        setSolid(true);
        spreadFactor = 0.01;
    }

}

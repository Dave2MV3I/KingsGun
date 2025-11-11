package my_project.model.DungeonMode;

import KAGO_framework.model.GraphicalObject;
import my_project.model.DungeonMode.Tiles.Tile;
import my_project.model.DungeonMode.Tiles.TileFloor;
import my_project.model.DungeonMode.Tiles.TileWall;

import java.awt.image.BufferedImage;

/** Dungeon Object, which
 *
 */
public class Dungeon extends GraphicalObject {
    private Monster monster;
    private Tile[][] tiles;

    public Dungeon() {
        this.monster = new Monster();
        setMap("src/main/resources/graphic/Dungeon 1.png");
    }

    /**
     * Creates a Map for the Dungeon according to the inputted image:
     * <br>
     * <br>
     * Black Pixel: Wall
     * <br>
     * White Pixel: Floor
     *
     * <br>
     * Red Pixel: Moster spawn
     * <br>
     * Magenta Pixel: Cosmetic Tile
     * <br>
     * Blue Pixel: Player spawn
     * <br>
     * Cyan Pixel: Chest Tile
     * <br>
     * Green Pixel: Loot Tile
     * @param map
     */
    private void setMap(BufferedImage map){
        int width = map.getWidth();
        int height = map.getHeight();
        this.tiles = new Tile[width][height];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                switch (Integer.toBinaryString(map.getRGB(col, row))) { //aaaaaaaabbbbbbbbrrrrrrrrgggggggg
                    case "11111111000000000000000000000000": //black
                        tiles[col][row] = new TileWall(col, row);
                        ; break;
                    case "11111111111111110000000011111111": //magenta

                        ; break;
                    case "11111111111111110000000000000000": //red
                        ; break;
                    case "11111111111111111111111100000000": //yellow
                        ; break;
                    case "11111111000000001111111100000000": //green
                        ; break;
                    case "11111111000000001111111111111111": //cyan
                        ; break;
                    case "11111111000000000000000011111111": //blue
                        ; break;
                    case "11111111111111111111111111111111": //white
                        tiles[col][row] = new TileFloor(col, row);
                        ; break;
                }

            }
        }
    }
    private void setMap(String path){
        setMap(createImage(path));

    }
    public Tile[][] getTiles(){
        return tiles;
    }

}

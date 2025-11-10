package my_project.model.DungeonMode;

import KAGO_framework.Config;
import KAGO_framework.model.GraphicalObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/** Dungeon Object, which
 *
 */
public class Dungeon extends GraphicalObject {
    private Monster monster;
    private Chunk[][] chunks;

    public Dungeon() {
        this.monster = new Monster();
        this.chunks = new Chunk[8][8];
        setMap("src/main/resources/graphic/testImage.png");
    }

    /**
     * Creates a Map for the Dungeon according to the inputted image:
     * <br>
     * <br>
     * Black Pixel: Wall
     * <br>
     * White Pixel: Floor
     * <br>
     * Red Pixel: Moster spawn
     * <br>
     * Blue Pixel: Chest spawn
     * <br>
     * Green Pixel: Loot position
     * @param map
     */

    private void setMap(BufferedImage map){
        int width = map.getWidth();
        int height = map.getHeight();
        int[][] tilePixels = new int[width][height];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tilePixels[col][row] = map.getRGB(col, row);

                switch (Integer.toBinaryString(tilePixels[col][row])) { //aaaaaaaabbbbbbbbrrrrrrrrgggggggg
                    case "11111111000000000000000000000000": System.out.print("Black "); break;
                    case "11111111111111110000000011111111": System.out.print("Magenta "); break;
                    case "11111111111111110000000000000000": System.out.print("Red "); break;
                    case "11111111111111111111111100000000": System.out.print("Yellow "); break;
                    case "11111111000000001111111100000000": System.out.print("Green "); break;
                    case "11111111000000001111111111111111": System.out.print("Cyan "); break;
                    case "11111111000000000000000011111111": System.out.print("Blue "); break;
                    case "11111111111111111111111111111111": System.out.print("White "); break;
                }

            }
            System.out.println();
        }
    }
    private void setMap(String path){
        setMap(createImage(path));

    }

}

package my_project.model.DungeonMode;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.control.modeControl.DungeonModeControl;
import my_project.model.DungeonMode.Monsters.*;
import my_project.model.DungeonMode.Monsters.Monster;
import my_project.model.DungeonMode.Tiles.Tile;
import my_project.model.DungeonMode.Tiles.TileFloor;
import my_project.model.DungeonMode.Tiles.TileWall;

import java.awt.image.BufferedImage;

/** Dungeon Object, which
 *
 */
public class Dungeon extends GraphicalObject {
    private Tile[][] tiles;

    private final DungeonModeControl control;
    private Monster monster;

    public Dungeon(DungeonModeControl control, List<Class<? extends Monster>> monsterClasses) {
        this.control = control;
        setMap("src/main/resources/graphic/Dungeon 1.png");
        // Choose random monster. Powerful monsters spawn with less probability.
        monsterClasses.toFirst();
        double nextMonsterProbability = Math.random();
        while(nextMonsterProbability < 0.4){
            if (monsterClasses.hasAccess()) {
                monsterClasses.next();
                nextMonsterProbability = Math.random();
            } else monsterClasses.toLast();
        }
        // Instantiate chosen monster
        Class<? extends Monster> chosenMonster = monsterClasses.getContent();
        try {
            java.lang.reflect.Constructor<? extends Monster> constructor = chosenMonster.getDeclaredConstructor();
            monster = constructor.newInstance();
            System.out.println("Neues Monster gespawnt: " + chosenMonster);
        } catch (NoSuchMethodException e) {
            System.err.println("Error: Monster class does not have a parameterless constructor.");
            e.printStackTrace();
        } catch (Exception e) {
            // Catche InstantiationException, IllegalAccessException, InvocationTargetException ab
            System.err.println("Error with instantiating the monster: " + e.getMessage());
            e.printStackTrace();
        }
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
     * Red Pixel: Monster spawn
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
                switch (Integer.toBinaryString(map.getRGB(col, row))) { //aaaaaaaarrrrrrrrggggggggbbbbbbbb
                    case "11111111000000000000000000000000": //black: wall
                        tiles[col][row] = new TileWall(col, row, this);
                        break;
                    case "11111111111111110000000011111111": //magenta

                        break;
                    case "11111111111111110000000000000000": //red

                        break;
                    case "11111111111111111111111100000000": //yellow

                        break;
                    case "11111111000000001111111100000000": //green

                        break;
                    case "11111111000000001111111111111111": //cyan

                        break;
                    case "11111111000000000000000011111111": //blue

                        break;
                    case "11111111111111111111111111111111": //white
                        tiles[col][row] = new TileFloor(col, row, this);
                        break;
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
    public Tile getTile(int x, int y){
        return tiles[x][y];
    }
    public Tile getTileFromCoordinates(double x, double y){
        return getTile((int)(x/Tile.getWIDTH()), (int)(y/Tile.getHEIGHT()));
    }
    public Monster getMonster(){return this.monster;}
    @Override
    public void update(double dt) {
        super.update(dt);
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile != null) {
                    tile.update(dt);
                }
            }
        }
    }
}

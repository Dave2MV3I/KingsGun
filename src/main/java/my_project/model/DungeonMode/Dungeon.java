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
    private Monster monster;
    private Tile[][] tiles;
    private DungeonModeControl control;
    List<Monster> monsters;
    private static Monster lastMonster;
    private Monster myMonster;

    public Dungeon(DungeonModeControl control) {
        this.control = control;
        monsters = control.monsters;

        //this.monster = new Monster();
        setMap("src/main/resources/graphic/Dungeon 1.png");
        //int randomMonster = (int)(Math.random()*8);

        double randomMonster = Math.random();
        while(randomMonster < 0.4){
            if (monsters.getContent() != lastMonster) {
                monsters.next();
                randomMonster = Math.random();
            }
        }
        myMonster = monsters.getContent();
    }

    /**
     * Adds all types of monsters to the linked list
     */
    public void instantiateMonsters(){
        //TODO David: Instead of instantiating all monsters in the beginning and putting them into a list,
        // having a list with class names or numbers for each class, choose a class mid-game and
        // instantiate the needed monster of this class
        monsters.append(new Dragon());
        monsters.append(new Goblin());
        monsters.append(new Orc());
        monsters.append(new Dwarf());
        monsters.append(new Elf());
        monsters.append(new Ogre());
        monsters.append(new Troll());

        monsters.toLast();
        lastMonster = monsters.getContent();
        monsters.toFirst();
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

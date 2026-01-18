package my_project.modes.dungeonMode;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.modes.dungeonMode.Attacks.AttackRepresentation;
import my_project.modes.dungeonMode.Monsters.Monster;
import my_project.modes.dungeonMode.Tiles.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/** Dungeon Object, which
 *
 */
public class Dungeon extends GraphicalObject {
    private Tile[][] tiles;

    private final DungeonModeControl control;
    private Monster monster;
    private final List<Class<? extends Monster>> monsterClasses;
    private ArrayList<AttackRepresentation> currentAttacks = new ArrayList<>();

    public Dungeon(DungeonModeControl control, List<Class<? extends Monster>> monsterClasses) {
        this.control = control;
        this.monsterClasses = monsterClasses;
        setMap("src/main/resources/graphic/Dungeon "+((int)(Math.random()*5)+1)+".png");
    }

    private Monster generateMonster(double x, double y){
        monsterClasses.toFirst();
        double nextMonsterProbability = Math.random();
        while(nextMonsterProbability < 0.4){
            monsterClasses.next();
            if (!monsterClasses.hasAccess()) {
                monsterClasses.toLast();
                break;
            }
            nextMonsterProbability = Math.random();
        }
        // Instantiate chosen monster
        Class<? extends Monster> chosenMonster = monsterClasses.getContent();
        try {
            java.lang.reflect.Constructor<? extends Monster> constructor = chosenMonster.getDeclaredConstructor(DungeonModeControl.class);
            constructor.setAccessible(true);
            System.out.println("Neues Monster gespawnt: " + chosenMonster);

            Monster temp = constructor.newInstance(control); // Monster werden jetzt in einer eigenen Funktion Generiert und hier zurück gegeben.
            temp.setPosition(x, y);
            return temp;
        } catch (NoSuchMethodException e) {
            System.err.println("Error: Monster class does not have a parameterless constructor.");
            e.printStackTrace();
        } catch (Exception e) {
            // Catche InstantiationException, IllegalAccessException, InvocationTargetException ab
            System.err.println("Error with instantiating the monster: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
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
     * Blue Pixel: DungeonPlayer spawn
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
                switch (Integer.toBinaryString(map.getRGB(col, row))) {
                    case "11111111000000000000000000000000":
                        tiles[col][row] = new TileWall(col, row, this);
                        break;
                    case "11111111111111110000000011111111":
                        tiles[col][row] = new TileChest(col, row, this);
                        break;
                    case "11111111111111110000000000000000":
                        monster = generateMonster(col * Tile.getWIDTH(), row* Tile.getHEIGHT());

                        tiles[col][row] = new TileFloor(col, row, this);
                        break;
                    case "11111111111111111111111100000000":
                        getPlayer().setX((col+0.5) * Tile.getWIDTH());
                        getPlayer().setY((row+0.5) * Tile.getHEIGHT());
                        tiles[col][row] = new TileFloor(col, row, this);
                        break;
                    case "11111111000000001111111100000000": //green
                        tiles[col][row] = new TileCrate(col, row, this);
                        break;
                    case "11111111000000001111111111111111": //cyan
                        tiles[col][row] = new TileDoor(col, row, this);
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
        if (x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length) {
            return tiles[x][y];
        }
        return null;
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

    public DungeonPlayer getPlayer() {
        return control.getDungeonPlayer();
    }
    public ArrayList<AttackRepresentation> getCurrentAttacks(){return currentAttacks;}
}

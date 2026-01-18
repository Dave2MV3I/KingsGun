package my_project.model.Graphics;

import com.sun.javafx.geom.Vec2d;

import java.awt.image.BufferedImage;


/**By Joshua Becker
 */
public class TileSheet extends SpriteSheet{
    private Vec2d[][][][][][][][] tileOrientations;
    private boolean[] currentBool;
    private Vec2d nullTileOrientation;
    public TileSheet(String tileSheet) {
        super(tileSheet, 6, 7);
        setTileOrientations();
        addTileOrientationExceptions(createImage("src/main/resources/graphic/TileSheetExceptions.png"));
        addTileOrientationExceptions(createImage("src/main/resources/graphic/CornerTileExceptions.png"));

        //setCurrent(1,1);
    }
    /**
     * | 8 | 1 | 2 |<br>
     * | 7 | T | 3 |<br>
     * | 6 | 5 | 4 |<br>
     *
     */
    public Vec2d getTileOrientation(boolean dir1, boolean dir2, boolean dir3, boolean dir4, boolean dir5, boolean dir6, boolean dir7, boolean dir8) {
        return getTileOrientation(new boolean[]{dir1, dir2, dir3, dir4, dir5, dir6, dir7, dir8});
    }
    public void setCurrent(boolean[] surroundingTiles) {
        /*
        System.out.println("--- Atempting to get:");
        System.out.println(booltoBin(surroundingTiles[7])+ " " + booltoBin(surroundingTiles[0]) + " " + booltoBin(surroundingTiles[1]));
        System.out.println(booltoBin(surroundingTiles[6])+ " " + "T" + " " + booltoBin(surroundingTiles[2]));
        System.out.println(booltoBin(surroundingTiles[5])+ " " + booltoBin(surroundingTiles[4])+ " " + booltoBin(surroundingTiles[3]));
         */
        currentBool = surroundingTiles;
        Vec2d current = getTileOrientation(currentBool);
        if (current == null) {
           current = nullTileOrientation;
           //System.out.println();
        }
        currentX = (int)current.x;
        currentY = (int)current.y;
        //System.out.println(currentX + " " + currentY);
        setImageToCurrent();
    }
    public Vec2d getTileOrientation(boolean[] directions) {
        int[] intDir = new int[directions.length];
        for (int i = 0; i < directions.length; i++) {
            intDir[i] = directions[i] ? 1 : 0;
        }
        return getTileOrientation(intDir);
    }
    private Vec2d getTileOrientation(int[] directions) {
        return tileOrientations[directions[0]][directions[1]][directions[2]][directions[3]][directions[4]][directions[5]][directions[6]][directions[7]];
    }
    private void setTileOrientations() {
        //hier ist alles richtig
        tileOrientations = new Vec2d[2][2][2][2][2][2][2][2];
        nullTileOrientation = new Vec2d(5, 0);
        BufferedImage tileOrientationTexture = createImage("src/main/resources/graphic/TileSheetOrientations.png");
        for (int iX = 0; iX < (tileOrientationTexture.getWidth()/3); iX++) {
            for (int iY = 0; iY < (tileOrientationTexture.getHeight()/3); iY++) {
                setTileOrientation(tileOrientationTexture.getSubimage(iX*3, iY*3, 3, 3), iX, iY);
            }
        }
    }
    private void addTileOrientationExceptions(BufferedImage exceptions){
        //TODO set tileOrientationException
        for (int iY = 0; iY < (exceptions.getHeight()/3); iY++) {
            setTileOrientationException(exceptions.getSubimage(0, iY * 3,6,3));
        }
    }
    private void setTileOrientationException(BufferedImage group) {
        BufferedImage exceptionSheet = group.getSubimage(0, 0, 3, 3);
        BufferedImage replacementSheet = group.getSubimage(3, 0, 3, 3);
        boolean[] exceptionDirection = new boolean[]{
                getPixelvalue(exceptionSheet, 1, 0),
                getPixelvalue(exceptionSheet, 2, 0),
                getPixelvalue(exceptionSheet, 2, 1),
                getPixelvalue(exceptionSheet, 2, 2),
                getPixelvalue(exceptionSheet, 1, 2),
                getPixelvalue(exceptionSheet, 0, 2),
                getPixelvalue(exceptionSheet, 0, 1),
                getPixelvalue(exceptionSheet, 0, 0)
        };
        boolean[] replacementsDirection = new boolean[]{
                getPixelvalue(replacementSheet, 1, 0),
                getPixelvalue(replacementSheet, 2, 0),
                getPixelvalue(replacementSheet, 2, 1),
                getPixelvalue(replacementSheet, 2, 2),
                getPixelvalue(replacementSheet, 1, 2),
                getPixelvalue(replacementSheet, 0, 2),
                getPixelvalue(replacementSheet, 0, 1),
                getPixelvalue(replacementSheet, 0, 0)
        };
        Vec2d replacementPosition = getTileOrientation(replacementsDirection);
        if (replacementPosition != null) {
            setTileOrientation(exceptionDirection, (int)(replacementPosition.x), (int)(replacementPosition.y));
        }else {
            System.out.println("Tile Orientation Exception: ");
            outDirections(exceptionDirection);
            System.out.println("Replacement: ");
            outDirections(replacementsDirection);
            System.out.println("");
        }
    }
    private void setTileOrientation(BufferedImage tile, int x, int y) {

        boolean[] directions = new boolean[]{
                getPixelvalue(tile, 1, 0),
                getPixelvalue(tile, 2, 0),
                getPixelvalue(tile, 2, 1),
                getPixelvalue(tile, 2, 2),
                getPixelvalue(tile, 1, 2),
                getPixelvalue(tile, 0, 2),
                getPixelvalue(tile, 0, 1),
                getPixelvalue(tile, 0, 0)
        };
        if (Integer.toBinaryString(tile.getRGB(1, 1)).equals("11111111111111111111111100000000")) {
            setTileOrientation(directions, x, y);
            /*
            System.out.println("-------");
            System.out.println("Added tile orientation " + x + " " + y);

            System.out.println(booltoBin(directions[7])+ " " + booltoBin(directions[0]) + " " + booltoBin(directions[1]));
            System.out.println(booltoBin(directions[6])+ " " + "T" + " " + booltoBin(directions[2]));
            System.out.println(booltoBin(directions[5])+ " " + booltoBin(directions[4])+ " " + booltoBin(directions[3]));
            */
        }
    }
    private int booltoBin(boolean bool){
        return bool ? 1 : 0;
    }
    private void outDirections(boolean[] surroundingTiles){
        System.out.println(booltoBin(surroundingTiles[7])+ " " + booltoBin(surroundingTiles[0]) + " " + booltoBin(surroundingTiles[1]));
        System.out.println(booltoBin(surroundingTiles[6])+ " " + "T" + " " + booltoBin(surroundingTiles[2]));
        System.out.println(booltoBin(surroundingTiles[5])+ " " + booltoBin(surroundingTiles[4])+ " " + booltoBin(surroundingTiles[3]));
    }
    private boolean getPixelvalue(BufferedImage tile, int px, int py) {
        return switch (Integer.toBinaryString(tile.getRGB(px, py))) { //aaaaaaaarrrrrrrrggggggggbbbbbbbb
            case "11111111111111110000000000000000" -> //red
                    false;
            case "11111111000000001111111100000000" -> //green
                    true;
            default -> false;
        };
    }
    /**
     * | 8 | 1 | 2 |<br>
     * | 7 | T | 3 |<br>
     * | 6 | 5 | 4 |<br>
     *
     */
    private void setTileOrientation(int d1, int d2, int d3, int d4, int d5, int d6, int d7, int d8, int posX, int posY) {
        setTileOrientation(new int[]{d1,d2,d3,d4,d5,d6,d7,d8}, posX, posY);
    }
    /**
     * | 8 | 1 | 2 |<br>
     * | 7 | T | 3 |<br>
     * | 6 | 5 | 4 |<br>
     *
     */
    private void setTileOrientation(int[] directions, int posX, int posY) {
        tileOrientations[directions[0]][directions[1]][directions[2]][directions[3]][directions[4]][directions[5]][directions[6]][directions[7]] = new Vec2d(posX, posY);
    }
    private void setTileOrientation(boolean[] directions, int posX, int posY) {
        int[] intDir = new int[directions.length];
        for (int i = 0; i < directions.length; i++) {
            intDir[i] = directions[i] ? 1 : 0;
        }
        setTileOrientation(intDir, posX, posY);
    }

}

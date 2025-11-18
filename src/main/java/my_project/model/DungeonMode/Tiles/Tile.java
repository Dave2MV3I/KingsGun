package my_project.model.DungeonMode.Tiles;

import KAGO_framework.view.DrawTool;
import my_project.model.CoreClasses.GameObject;
import my_project.model.DungeonMode.Dungeon;
import my_project.model.Graphics.Texture;
import my_project.model.Graphics.TileSheet;

import java.util.Objects;

public abstract class Tile extends GameObject {
    private static final double WIDTH = 16;
    private static final double HEIGHT = 16;
    private Dungeon dungeon;
    private boolean isSolid;


    public Tile(int x, int y, Texture texture, Dungeon dungeon) {
        setDungeon(dungeon);
        this.x = x * WIDTH;
        this.y = y * HEIGHT;
        this.texture = texture;

        setSize();
    }
    private void setSize() {
        this.width = WIDTH;
        this.height = HEIGHT;
    }
    public static double getWIDTH() {
        return WIDTH;
    }
    public static double getHEIGHT() {
        return HEIGHT;
    }
    @Override
    public void draw(DrawTool drawTool) {
        if(this.texture != null) {
            this.texture.autoDraw(drawTool, x, y, getWIDTH());
        }

    }
    @Override
    public void update(double dt) {
        super.update(dt);
        findOrientation();
        texture.update(dt);
    }
    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
    public boolean isSolid() {
        return isSolid;
    }
    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }
    public int getGridPosX() {
        return (int)(x/WIDTH);
    }
    public int getGridPosY() {
        return (int)(y/WIDTH);
    }
    public Tile getRelative(int relativeX, int relativeY) {
        return dungeon.getTile(getGridPosX() + relativeX, getGridPosY() + relativeY);
    }
    public Tile getRelative(String direction) {
        return switch (direction) {
            case "up" -> dungeon.getTile(getGridPosX(), getGridPosY() - 1);
            case "topRight" -> dungeon.getTile(getGridPosX() + 1, getGridPosY() - 1);
            case "right" -> dungeon.getTile(getGridPosX() + 1, getGridPosY());
            case "downRight" -> dungeon.getTile(getGridPosX() + 1, getGridPosY() + 1);
            case "down" -> dungeon.getTile(getGridPosX(), getGridPosY() + 1);
            case "downLeft" -> dungeon.getTile(getGridPosX() - 1, getGridPosY() + 1);
            case "left" -> dungeon.getTile(getGridPosX() - 1, getGridPosY());
            case "upLeft" -> dungeon.getTile(getGridPosX() - 1, getGridPosY() - 1);
            default -> null;
        };
    }
    private void findOrientation(){
        if(texture instanceof TileSheet) {
            Tile[] relativeTiles = new Tile[]{
                getRelative("up"),
                getRelative("upRight"),
                getRelative("right"),
                getRelative("downRight"),
                getRelative("down"),
                getRelative("downLeft"),
                getRelative("left"),
                getRelative("upLeft")
            };
            boolean[] tempBool = new boolean[8];
            for (int i = 0; i < relativeTiles.length; i++) {
                tempBool[i] = relativeTiles[i] != null && relativeTiles[i].getClass().equals(getClass());
            }

            ((TileSheet)texture).setCurrent(tempBool);

        }
    }
}

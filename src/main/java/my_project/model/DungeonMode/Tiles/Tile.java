package my_project.model.DungeonMode.Tiles;

import KAGO_framework.view.DrawTool;
import my_project.model.CoreClasses.GameObject;
import my_project.model.DungeonMode.Dungeon;
import my_project.model.Graphics.Texture;
import my_project.model.Graphics.TileSheet;

import java.awt.*;
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
            //drawOrientation(drawTool, x, y);
        }

    }
    @Override
    public void update(double dt) {
        super.update(dt);

        setOrientation(findOrientation());
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
            case "upRight" -> dungeon.getTile(getGridPosX() + 1, getGridPosY() - 1);
            case "right" -> dungeon.getTile(getGridPosX() + 1, getGridPosY());
            case "downRight" -> dungeon.getTile(getGridPosX() + 1, getGridPosY() + 1);
            case "down" -> dungeon.getTile(getGridPosX(), getGridPosY() + 1);
            case "downLeft" -> dungeon.getTile(getGridPosX() - 1, getGridPosY() + 1);
            case "left" -> dungeon.getTile(getGridPosX() - 1, getGridPosY());
            case "upLeft" -> dungeon.getTile(getGridPosX() - 1, getGridPosY() - 1);
            default -> null;
        };
    }
    private boolean[] findOrientation(){
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
            return tempBool;
        }
        return null;
    }
    private void setOrientation(boolean[] orientation) {
        if(texture instanceof TileSheet) ((TileSheet)texture).setCurrent(orientation);
    }

    /** test function to troubleshoot the Orientation detection
     *
     * @param drawTool
     * @param dx
     * @param dy
     */
    public void drawOrientation(DrawTool drawTool, double dx, double dy) {
        if(texture instanceof TileSheet) {
            double wh = 16/3-1;
            boolean[] o = findOrientation();
            assert o != null;
            if(o[0]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx, dy, wh, wh);
            if(o[1]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx + wh, dy, wh, wh);
            if(o[2]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx + wh, dy + wh, wh, wh);
            if(o[3]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx + wh, dy + 2*wh, wh, wh);
            if(o[4]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx, dy + 2*wh, wh, wh);
            if(o[5]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx - wh, dy + 2*wh, wh, wh);
            if(o[6]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx - wh, dy + wh, wh, wh);
            if(o[7]) drawTool.setCurrentColor(new Color(0, 255, 0));else drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawFilledRectangle(dx - wh, dy, wh, wh);
        }
    }
}

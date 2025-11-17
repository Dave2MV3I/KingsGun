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
    protected String textureType;


    public Tile(int x, int y, Texture texture) {

        this.x = x * WIDTH;
        this.y = y * HEIGHT;
        this.texture = texture;
        textureType = texture.getClass().getSimpleName();
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
        switch (direction) {
            case "up":
                return dungeon.getTile(getGridPosX(), getGridPosY() - 1);
            case "topRight":
                return dungeon.getTile(getGridPosX() + 1, getGridPosY() - 1);
            case "right":
                return dungeon.getTile(getGridPosX() + 1, getGridPosY());
            case "downRight":
                return dungeon.getTile(getGridPosX()+1, getGridPosY() + 1);
            case "down":
                return dungeon.getTile(getGridPosX(), getGridPosY() + 1);
            case "downLeft":
                return dungeon.getTile(getGridPosX()-1, getGridPosY() + 1);
            case "left":
                return dungeon.getTile(getGridPosX() - 1, getGridPosY());
            case "upLeft":
                return dungeon.getTile(getGridPosX()-1, getGridPosY() - 1);
        }
        return null;
    }
    private void findOrientation(){
        if(textureType.equals("TileSheet")) {
            boolean[] tempBool = new boolean[]{
                    getRelative("up").getClass().equals(getClass()),
                    getRelative("upRight").getClass().equals(getClass()),
                    getRelative("right").getClass().equals(getClass()),
                    getRelative("downRight").getClass().equals(getClass()),
                    getRelative("down").getClass().equals(getClass()),
                    getRelative("downLeft").getClass().equals(getClass()),
                    getRelative("left").getClass().equals(getClass()),
                    getRelative("upLeft").getClass().equals(getClass())
            };
            ((TileSheet)texture).getTileOrientation(tempBool);
        }
    }
}

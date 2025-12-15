package my_project.modes.dungeonMode.Tiles;

import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.modes.dungeonMode.Dungeon;
import my_project.model.Graphics.Texture;
import my_project.model.Graphics.TileSheet;
import my_project.view.MainView;

import java.awt.*;

public abstract class Tile extends GameObject {
    private static final double WIDTH = 32;
    private static final double HEIGHT = 32;
    private Dungeon dungeon;
    private boolean isSolid;
    protected double brightness; // 0.0 - 1.0
    protected double spreadFactor;


    public Tile(int x, int y, Texture texture, Dungeon dungeon) {
        setDungeon(dungeon);
        this.x = x * WIDTH;
        this.y = y * HEIGHT;
        this.texture = texture;
        this.brightness = Math.random();
        setSize();
        spreadFactor = 0.8;
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
            drawTool.setCurrentColor(new Color(0, 0, 0, (int)(255*(1-brightness))));
            drawTool.drawFilledRectangle(MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), MainView.scale(getWIDTH()), MainView.scale(getHEIGHT()));
            brightness = brightness * 0.9;
        }

    }
    @Override
    public void update(double dt) {
        super.update(dt);
        spreadBrightness();

        setOrientation(findOrientation());
        texture.update(dt);
    }
    public void setBrightness(double brightness) {
        this.brightness = Math.min(Math.max(brightness, 0), 1);
    }
    public void drawBrightness(DrawTool drawTool) {
        //TODO make BrightnessGradient
        double ftr = 0.0;
        double fbr = 0.0;
        double fbl = 0.0;
        double ftl = 0.0;
        drawScaledRectangle(drawTool, x, y, getWIDTH()/2, getHEIGHT()/2);
        drawScaledRectangle(drawTool, x+getWIDTH()/2, y, getWIDTH()/2, getHEIGHT()/2);
        drawScaledRectangle(drawTool, x+getWIDTH()/2, y+getHEIGHT()/2, getWIDTH()/2, getHEIGHT()/2);
        drawScaledRectangle(drawTool, x, y+getHEIGHT()/2, getWIDTH()/2, getHEIGHT()/2);

    }
    private void drawScaledRectangle(DrawTool drawTool, double rx, double ry, double rw, double rh) {
        drawTool.drawFilledRectangle( rx, ry, rw, rh);
    }
    public void increaseBrightnessTo(double brightness) {
        if (this.brightness > brightness) return;
        this.brightness = Math.min(Math.max(brightness, 0), 1);
    }
    public void spreadBrightness() {
        if (brightness > 0) {
            double diagonalSpreadFactor = spreadFactor / Math.sqrt(2);
            if (getRelative("up") != null) getRelative("up").increaseBrightnessTo(this.brightness * spreadFactor);
            if (getRelative("upRight") != null) getRelative("upRight").increaseBrightnessTo(this.brightness * diagonalSpreadFactor);
            if (getRelative("right") != null) getRelative("right").increaseBrightnessTo(this.brightness * spreadFactor);
            if (getRelative("downRight") != null) getRelative("downRight").increaseBrightnessTo(this.brightness * diagonalSpreadFactor);
            if (getRelative("down") != null) getRelative("down").increaseBrightnessTo(this.brightness * spreadFactor);
            if (getRelative("downLeft") != null) getRelative("downLeft").increaseBrightnessTo(this.brightness * diagonalSpreadFactor);
            if (getRelative("left") != null) getRelative("left").increaseBrightnessTo(this.brightness * spreadFactor);
            if (getRelative("upLeft") != null) getRelative("upLeft").increaseBrightnessTo(this.brightness * diagonalSpreadFactor);
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

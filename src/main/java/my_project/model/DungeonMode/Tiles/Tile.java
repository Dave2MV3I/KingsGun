package my_project.model.DungeonMode.Tiles;

import KAGO_framework.view.DrawTool;
import my_project.model.CoreClasses.GameObject;
import my_project.model.Graphics.Texture;
import my_project.model.Graphics.TileSheet;

public abstract class Tile extends GameObject {
    private static final double WIDTH = 16;
    private static final double HEIGHT = 16;

    public Tile(int x, int y, TileSheet tileSheet) {
        this.x = x * WIDTH;
        this.y = y * HEIGHT;
        setSize();
    }
    public Tile(int x, int y, Texture texture) {
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
}

package my_project.model.Graphics;

public class TileSheet extends SpriteSheet{
    public TileSheet(String tileSheet) {
        super(tileSheet, 6, 7);
        setCurrent(1,1);
    }

}

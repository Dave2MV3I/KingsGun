package my_project.model.Graphics;

import KAGO_framework.model.abitur.datenstrukturen.BinaryTree;

import java.awt.image.BufferedImage;

public class TileSheet extends SpriteSheet{
    private BinaryTree<BufferedImage> tileOrientations;
    public TileSheet(String tileSheet) {
        super(tileSheet, 6, 7);
        setCurrent(1,1);
    }
    /**
     * | 8 | 1 | 2 |<br>
     * | 7 | T | 3 |<br>
     * | 6 | 5 | 4 |<br>
     *
     */
    public BufferedImage getTileOrientation(boolean dir1, boolean dir2, boolean dir3, boolean dir4, boolean dir5, boolean dir6, boolean dir7, boolean dir8) {
        return getTileOrientation(new boolean[]{dir1, dir2, dir3, dir4, dir5, dir6, dir7, dir8});
    }
    public BufferedImage getTileOrientation(boolean[] directions) {
        int i = 0;
        BinaryTree<BufferedImage> currentNode = tileOrientations;
        while (i < directions.length && i < 8 && !currentNode.isEmpty()) {
            if (directions[i]) {
                currentNode = currentNode.getRightTree();
            } else {
                currentNode = currentNode.getLeftTree();
            }
            i++;
        }
        return currentNode.getContent();
    }
    private void setTileOrientation() {
        tileOrientations = new BinaryTree<>();
        //TODO something ig I HAVE TO DO THIS
    }
}

package my_project.model.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet extends Texture {
    BufferedImage[][] subImages;
    private int currentX;
    private int currentY;
    private double subImgWidth;
    private double subImgHeight;
    private double frameCooldownX;
    private double frameCooldownY;
    private double frameTimerX;
    private double frameTimerY;

    /** Creates a SpriteSheet Object
     *
     * @param spriteSheet path to the Imagefile relative from src/my_project/resources/
     * @param rows amount of rows in the Sprite Sheet
     * @param cols amount of columns in the Sprite Sheet
     */
    private SpriteSheet(String spriteSheet, int rows, int cols) {
        super(spriteSheet);
        subImages = new BufferedImage[rows][cols];
        currentX = 0;
        currentY = 0;
        subImgWidth = (double) getMyImage().getWidth() /cols;
        subImgHeight = (double) getMyImage().getHeight() /rows;

        frameCooldownX = 0;
        frameCooldownY = 0;
        frameTimerX = 0;
        frameTimerY = 0;

        for (int iX = 0; iX < cols; iX++) {
            for (int iY = 0; iY < rows; iY++) {
                subImages[iX][iY] = getMyImage().getSubimage((int)(iX*subImgWidth), (int)(iY*subImgHeight), (int)(subImgWidth), (int)(subImgHeight));
            }
        }
    }
    @Override
    public void update(double dt){
        super.update(dt);
        if (frameCooldownX != 0) {
            frameTimerX += dt;
            if (Math.abs(frameCooldownX) < frameTimerX) {
                frameTimerX %= Math.abs(frameCooldownX); //reset the timer
                if (frameCooldownX > 0) {
                    currentX += 1;
                } else {
                    currentX -= 1;
                }
            }
        }
        if (frameCooldownY != 0) {
            frameTimerY += dt;
            if (Math.abs(frameCooldownY) < frameTimerY) {
                frameTimerY %= Math.abs(frameCooldownY); //reset the timer
                if (frameCooldownY > 0) {
                    currentY += 1;
                } else {
                    currentY -= 1;
                }
            }
        }
        currentX %= subImages.length;
        currentY %= subImages[0].length;
    }

    //Getter and Setter
    public void setCurrent(int x, int y) {
        currentX = x;
        currentY = y;
    }
    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }
    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return currentX;
    }
    public int getCurrentY() {
        return currentY;
    }

    public double getSubImgWidth() {
        return subImgWidth;
    }
    public double getSubImgHeight() {
        return subImgHeight;
    }

}

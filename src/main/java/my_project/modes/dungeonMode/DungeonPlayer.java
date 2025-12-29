package my_project.modes.dungeonMode;

import KAGO_framework.view.DrawTool;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.dungeonMode.Tiles.InteractiveTile;
import my_project.modes.dungeonMode.Tiles.Tile;
import my_project.view.InputManager;
import my_project.view.MainView;

import java.awt.event.KeyEvent;

public class DungeonPlayer extends DungeonEntity {
    private double money;
    public DungeonPlayer(double x, double y, DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl);
        health = 100;
        money = 0;
        this.x = x;
        this.y = y;
        this.texture = new AnimatedSpriteSheet("Player.png", 4, 4);
        radius = 3;
        ((AnimatedSpriteSheet)texture).setCurrent(0,0);

        MainView.follow(x, y, true);
    }
    @Override
    public void update(double dt) {
        double lV = InputManager.isPressed(KeyEvent.VK_SHIFT) ? 50 : 30;
        double lVx = 0;
        double lVy = 0;
        //TODO Overhaul movment code sqrt(2) diagonal movement removal
        if (InputManager.isPressed("w") || InputManager.isPressed("s") || InputManager.isPressed("a") || InputManager.isPressed("d")) {
            ((AnimatedSpriteSheet)texture).setFrameCooldownX(0.1);
        }else{
            ((AnimatedSpriteSheet)texture).setFrameCooldownX(0);
            ((AnimatedSpriteSheet)texture).setCurrentX(0);
        }
        if (InputManager.isPressed("w")){
            lVy -= lV;
            ((AnimatedSpriteSheet) texture).setCurrentY(2);
        }
        if (InputManager.isPressed("s")) {
            lVy += lV;
            ((AnimatedSpriteSheet) texture).setCurrentY(0);
        }
        if (InputManager.isPressed("d")) {
            lVx += lV;
            ((AnimatedSpriteSheet) texture).setCurrentY(3);
        }
        if (InputManager.isPressed("a")) {
            lVx -= lV;
            ((AnimatedSpriteSheet) texture).setCurrentY(1);
        }

        setVelocityX(lVx);
        setVelocityY(lVy);
        texture.update(dt);
        super.update(dt);
        control.getTileByCoord(x, y).setBrightness(1);
        MainView.follow(x, y, true);
        if (InputManager.isPressed("f")) {
            int range = 1;
            for (int i = -range; i <= range; i++) {
                for (int j = -range; j <= range; j++) {
                    Tile locTile = control.getTileByCoord(x + (i * Tile.getWIDTH()), y + (j * Tile.getHEIGHT()));
                    if (locTile instanceof InteractiveTile) {
                        ((InteractiveTile)locTile).interact();
                    }
                }
            }
        }
        if (health < 0) {
            control.playerDied();
        }
    }
    @Override
    public void draw(DrawTool drawTool) {
        texture.autoDraw(drawTool, x - texture.getWidth()/2, y - 25, 32);
        autoDrawHitbox(drawTool);
    }
    public void drawHealth(DrawTool drawTool, double x, double y, double width, double height) {
        width = width * health/100;
        drawTool.drawFilledRectangle(x, y, width, height);
    }
    public void drawMoney(DrawTool drawTool, double x, double y) {
        drawTool.drawText(x, y, control.getPlayer().getMoney()+ "$ + " + money + "$");
    }
    public void increaseMoney(double amount) {
        money += amount;
    }
    public double getMoney() {
        return money;
    }

}

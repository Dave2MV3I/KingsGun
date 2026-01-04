package my_project.modes.dungeonMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.dungeonMode.Tiles.InteractiveTile;
import my_project.modes.dungeonMode.Tiles.Tile;
import my_project.view.InputManager;
import my_project.view.MainView;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DungeonPlayer extends DungeonEntity {
    private double money;
    private double exitTimer;
    private double exitTime;
    private double attackCooldown;
    public DungeonPlayer(double x, double y, DungeonModeControl dungeonModeControl) {
        super(dungeonModeControl);
        health = 100;
        attackCooldown = 0.0;
        exitTimer = 0.0;
        exitTime = 10.0;
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
        if (InputManager.isPressed("e")) {

            exitTimer += dt;
            if (exitTimer > exitTime) {
                control.exit();
            }
        } else {
            exitTimer = 0.0;
        }
        if (InputManager.isPressed("w") || InputManager.isPressed("s") || InputManager.isPressed("a") || InputManager.isPressed("d")) {
            ((AnimatedSpriteSheet)texture).setFrameCooldownX(0.1);
            exitTimer = 0.0;
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
        if (InputManager.isPressed("Space") && attackCooldown <= 0) {
            if (control.getMonster().getDistanceTo(this) < 128) {
                System.out.println("Monster is hit");
                control.getMonster().damage(100000);
                attackCooldown = 1;
            }
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
        attackCooldown -= dt;

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
    public void drawExit(DrawTool drawTool, double x, double y) {
        if (exitTimer != 0.0) {
            if (exitTimer <= 0.0){
                exitTimer = 0.0;
            }
            drawTool.setCurrentColor(new Color(211, 63, 8));
            double border = 25;
            drawTool.drawFilledRectangle(0, y - border, Config.WINDOW_WIDTH *(exitTimer/exitTime), Config.WINDOW_HEIGHT/10 + 2 * border);

            drawTool.setCurrentColor(new Color(255, 213, 100));
            drawTool.formatText("Arial", Font.BOLD, Config.WINDOW_HEIGHT/10);
            drawTool.drawText(Config.WINDOW_WIDTH/4, y + Config.WINDOW_HEIGHT/10, "EXITING IN: "+ ((Math.ceil((exitTime - exitTimer)*100))/100)); //TODO displays -0.01 to many seconds
        }
    }
    public void increaseMoney(double amount) {
        money += amount;
    }
    public double getMoney() {
        return money;
    }
    public double getExitTimer(){
        return exitTimer;
    }
    public double getExitTime(){
        return exitTime;
    }

}

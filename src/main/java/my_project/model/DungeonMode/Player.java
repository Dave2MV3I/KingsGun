package my_project.model.DungeonMode;

import KAGO_framework.control.ViewController;
import KAGO_framework.view.DrawTool;
import my_project.control.modeControl.DungeonModeControl;
import my_project.model.CoreClasses.GameObject;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.model.Graphics.SpriteSheet;
import my_project.model.Graphics.Texture;
import my_project.view.InputManager;
import my_project.view.MainView;

import javax.swing.text.View;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
    DungeonModeControl control;
    public Player(double x, double y, DungeonModeControl dungeonModeControl) {
        this.x = x;
        this.y = y;
        control = dungeonModeControl;
        this.texture = new AnimatedSpriteSheet("Player.png", 4, 4);
        radius = 3;
        ((AnimatedSpriteSheet)texture).setCurrent(0,0);
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
        MainView.follow(x, y, true);
    }
    @Override
    public void draw(DrawTool drawTool) {
        texture.autoDraw(drawTool, x - texture.getWidth()/2, y - 28, 32);
        autoDrawHitbox(drawTool);
    }
    @Override
    protected boolean movementCondition() {
        if (control.getTileByCoord(this.x, this.y) == null){
            return true;
        }
        return !control.getTileByCoord(this.x, this.y).isSolid();
    }
}

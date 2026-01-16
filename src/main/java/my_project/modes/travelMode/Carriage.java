package my_project.modes.travelMode;

import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.model.Graphics.Texture;
import my_project.view.InputManager;
import my_project.view.MainView;

public class Carriage extends GameObject {
    private AmmunitionInventory ammoInvent;
    private Gun gun;
    private double hP;
    public boolean outOfBounds = false;
    public Carriage() {
        ammoInvent = new AmmunitionInventory();
        gun = new Gun();
        this.texture = new Texture("carriage.png");
        x = 96;
        y = 500;

        width = 64;
        height = 96;
        hP = 100;
    }
    public void draw(DrawTool drawtool){
        texture.autoDraw(drawtool, x, y, 64);
        showHP(drawtool);
    }

    @Override
    public void update(double dt){
        super.update(dt);
        if(InputManager.isPressed("w")){
            moveY(-1);
        }
        if(InputManager.isPressed("a")){
            moveX(-20*dt);
        }
        if(InputManager.isPressed("s")){
            moveY(1);
        }
        if(InputManager.isPressed("d")){
            moveX(20*dt);
        }
        MainView.follow(0, y - 64, false); //folgt immer der Kutsche

        if (y<=-300){
            outOfBounds = true;
        }
    }

    public void loseHP(double damage){
        hP = hP - damage;
    }

    public void showHP(DrawTool drawTool){
        drawTool.setCurrentColor(255,255,255,255);
        drawTool.drawFilledRectangle(MainView.translateAndScaleX(x+texture.getWidth()/2), MainView.translateAndScaleY(y), MainView.scale(30), MainView.scale(8));
        drawTool.setCurrentColor(0,0,255,255);
        drawTool.drawFilledRectangle(MainView.translateAndScaleX(x+texture.getWidth()/2+1), MainView.translateAndScaleY(y+1), MainView.scale(28 - ((100-hP)*0.28) ), MainView.scale(6));

        drawTool.setCurrentColor(0,0,255,255);
        drawTool.drawText(MainView.translateAndScaleX(x+texture.getWidth()/2), MainView.translateAndScaleY(y), "" + hP);
    }

}

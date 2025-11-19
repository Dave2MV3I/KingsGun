package my_project.model.TravelMode;

import KAGO_framework.view.DrawTool;
import my_project.model.CoreClasses.GameObject;
import my_project.model.Graphics.Texture;
import my_project.view.InputManager;
import my_project.view.MainView;

public class Carriage extends GameObject {
    private AmmunitionInventory ammoInvent;
    private Gun gun;
    public Carriage() {
        ammoInvent = new AmmunitionInventory();
        gun = new Gun();
        this.texture = new Texture("carriage.png");
        x = 32;
        y = 500;
    }
    public void draw(DrawTool drawtool){
        texture.autoDraw(drawtool, x, y, 64);
    }

    @Override
    public void update(double dt){
        super.update(dt);
        if(InputManager.isPressed("w")){
            moveY(-1);
        }
        if(InputManager.isPressed("a")){
            moveX(-60*dt);
        }
        if(InputManager.isPressed("s")){
            moveY(1);
        }
        if(InputManager.isPressed("d")){
            moveX(60*dt);
        }
        MainView.follow(0, y, false); //folgt immer der Kutsche
    }

}

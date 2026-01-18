package my_project.modes.travelMode;

import KAGO_framework.model.abitur.datenstrukturen.Stack;
import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.model.Graphics.Texture;
import my_project.modes.travelMode.Ammunition.Ammunition;
import my_project.view.InputManager;
import my_project.view.MainView;

public class Carriage extends GameObject {
    private Gun gun;
    private double hP;
    public boolean outOfBounds = false;
    private Stack<Ammunition> ammo;
    private TravelModeControl travelModeControl;
    public Carriage(TravelModeControl control) {
        gun = new Gun(this);
        this.texture = new Texture("carriage.png");
        x = 96;
        y = 2400;
        travelModeControl = control;
        width = 34;
        height = 54;
        hP = control.getMainController().getCurrentPlayer().getCarHp();
    }
    public void draw(DrawTool drawtool){
        texture.autoDraw(drawtool, x-15, y-31, 64);
        gun.draw(drawtool);
        autoDrawHitbox(drawtool);
        showHP(drawtool);

    }

    @Override
    public void update(double dt){
        super.update(dt);
        if(InputManager.isPressed("w")){
            moveY(-40*dt);
        }
        if(InputManager.isPressed("a")){
            moveX(-20*dt);
        }
        if(InputManager.isPressed("d")){
            moveX(20*dt);
        }
        MainView.follow(0, y - 64, false); //folgt immer der Kutsche

        if (y<=-400){
            outOfBounds = true;
        }
        if (x>195){
            x = 195;
        }
        if (x<0){
            x = 0;
        }
        gun.update(dt);
        travelModeControl.getMainController().getCurrentPlayer().setCarHp(hP);
    }

    public void loseHP(double damage){
        hP = hP - damage;
    }

    public void showHP(DrawTool drawTool){
        drawTool.setCurrentColor(255,255,255,255);
        drawTool.drawFilledRectangle(MainView.translateAndScaleX(x+texture.getWidth()/2), MainView.translateAndScaleY(y), MainView.scale(30), MainView.scale(8));
        drawTool.setCurrentColor(0,0,255,255);
        drawTool.drawFilledRectangle(MainView.translateAndScaleX(x+texture.getWidth()/2+1), MainView.translateAndScaleY(y+1), MainView.scale(28 * (hP/500)), MainView.scale(6));

        drawTool.setCurrentColor(0,0,255,255);
        drawTool.drawText(MainView.translateAndScaleX(x+texture.getWidth()/2), MainView.translateAndScaleY(y), "" + hP);
    }

    public Stack<Ammunition> getAmmo() {
        return ammo;
    }

    public void setAmmo(Stack<Ammunition> ammo) {
        this.ammo = ammo;
    }

    public TravelModeControl getTravelModeControl() {
        return travelModeControl;
    }
    public double getHP() {
        return hP;
    }
}

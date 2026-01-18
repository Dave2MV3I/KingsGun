package my_project.modes.travelMode;


import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.model.Graphics.Texture;
import my_project.modes.travelMode.Ammunition.Ammunition;
import my_project.modes.travelMode.Ammunition.ElectricAmmunition;
import my_project.modes.travelMode.Ammunition.ExplosiveAmmunition;
import my_project.modes.travelMode.Ammunition.NormalAmmunition;
import my_project.view.InputManager;
import my_project.view.MainView;
import my_project.view.Mouse;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Gun extends GameObject {
    private double timer;
    private Carriage carriage;
    private List<Ammunition> shotsFired;
    private double direction;

    public Gun(Carriage carriage) {
        this.texture = new AnimatedSpriteSheet("Minigun.png", 1, 3);
        this.carriage = carriage;
        this.shotsFired = new List<>();
        this.x = carriage.getX() + carriage.getWidth() / 2;
        this.y = carriage.getY() + carriage.getHeight() / 2;
        this.radius = 16;
        this.direction = 0;
        ((AnimatedSpriteSheet) texture).setFrameCooldownX(0.1);
    }

    @Override
    public void update(double dt) {
        timer -= dt;
        this.x = carriage.getX() + carriage.getWidth() / 2;
        this.y = carriage.getY() + carriage.getHeight() / 2;
        texture.update(dt);
        double aimX = MainView.translateAndScaleX(Mouse.getPosition().x, true);
        double aimY = MainView.translateAndScaleY(Mouse.getPosition().y, true);
        this.direction = getDirection(aimX, aimY);

        if ((InputManager.isPressed(KeyEvent.VK_SPACE) || Mouse.isDown(1))&& timer <= 0) {
            timer = 0.1;
            if (carriage.getAmmo().top() != null) {
                System.out.println(carriage.getAmmo().top().getClass().getSimpleName());
                if (carriage.getAmmo().top().shoot(direction, x, y, carriage.getTravelModeControl().getBandits(), 200, 1)) {

                }else carriage.getAmmo().pop();

            }else {
                //no ammo (maybe sound)
            }
        }
    }
    @Override
    public void draw(DrawTool drawTool){
        autoDrawHitbox(drawTool);
        if(carriage.getAmmo().top() != null) {
            carriage.getAmmo().top().draw(drawTool);
        }
        texture.autoDraw(drawTool, x-this.radius, y-this.radius*2, this.radius*2, (direction/Math.PI)*180+90);
        //drawCursor(drawTool);
    }
    private void drawCursor(DrawTool drawTool){
        double mx = MainView.translateAndScaleX(Mouse.getPosition().x, true);
        double my = MainView.translateAndScaleY(Mouse.getPosition().y, true);
        drawTool.drawFilledCircle(MainView.translateAndScaleX(mx), MainView.translateAndScaleY(my), MainView.scale(5));
        drawTool.drawLine(Mouse.getPosition().x, Mouse.getPosition().y, MainView.translateAndScaleX(mx), MainView.translateAndScaleY(my));
    }
}
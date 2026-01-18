package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.settings.SettingsModel;
import my_project.view.MainView;

import java.awt.*;

/**Blueprint Class for all In-Game Objects
 *<br><br>
 *Joshua Becker
 */
public class GameObject extends GraphicalObject {
    protected Texture texture;
    private final double moveBackPrecisionFactor = 0.1;
    private double velocityX, velocityY;
    public GameObject() {}
    private void setVelocity(double x, double y){
        setVelocityX(x);
        setVelocityY(y);
    }
    /**
     * Sets the GameObjects velocity in a certain direction
     * @param angle The angle in which the GameObject should move
     * @param strength The amount the GameObject should move
     */
    protected void setVelocityAS(double angle, double strength){
        setVelocityX(Math.cos(angle)*strength);
        setVelocityY(Math.sin(angle)*strength);
    }
    protected double getDirection(GraphicalObject object){
        return getDirection((object.getX() + object.getWidth()/2),(object.getY() + object.getHeight()/2));
    }

    public double getDirection(double x, double y){
        return Math.atan2((y) - (this.getY() + this.getHeight()/2),  (x) - (this.getX() + this.getWidth()/2));
    }

    /**
     * sets the GameObjects velocity on the X-Axis
     * @param vx
     */
    protected void setVelocityX(double vx) {
        this.velocityX = vx;
    }
    /**
     * sets the GameObjects velocity on the Y-Axis
     * @param vy
     */
    protected void setVelocityY(double vy) {
        this.velocityY = vy;
    }

    /**
     * Moves the GameObject on the X-axis
     * @param mx amount to be moved on the X-axis
     */
    protected void moveX(double mx) {
        x += mx;
        while (!movementCondition()){
            x -= mx * moveBackPrecisionFactor;
        }
    }
    /**
     * Moves the GameObject on the Y-axis
     *
     * @param my amount to be moved on the Y-axis
     */
    protected void moveY(double my) {
        y += my;
        while (!movementCondition()){
            y -= my * moveBackPrecisionFactor;
        }
    }
    public boolean isOnScreen() {
        return (MainView.translateAndScaleX(x) >= MainView.scale(-getWidth()  -getRadius()) && MainView.translateAndScaleY(y) >= MainView.scale(-getHeight() -getRadius())) && (MainView.translateAndScaleX(x) < Config.WINDOW_WIDTH && MainView.translateAndScaleY(y) < Config.WINDOW_HEIGHT);
    }

    /**
     * Can be Overridden to incorporate Collision detection
     * @return whether the GameObject should move or not
     */
    protected boolean movementCondition(){
        return true;
    }

    @Override
    public void update(double dt) {
        if(velocityX != 0 && movementCondition()){
            moveX(velocityX * dt);
        }
        if(velocityY != 0 && movementCondition()){
            moveY(velocityY * dt);
        }
    }
    protected void drawHitbox(DrawTool drawTool) {
        if (SettingsModel.isDebugging()) {
            drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawRectangle(x, y, getWidth(), getHeight());
            drawTool.setCurrentColor(new Color(0, 0, 255));
            drawTool.drawCircle(x, y, getRadius());
            drawTool.setCurrentColor(new Color(255, 98, 0));
            drawTool.drawRectangle(x, y, getWidth(), getHeight());
            drawTool.setCurrentColor(new Color(0, 255, 255));
            drawTool.drawCircle(x, y, getRadius());
        }
    }
    protected void autoDrawHitbox(DrawTool drawTool) {
        if (SettingsModel.isDebugging()) {
            drawTool.setCurrentColor(new Color(255, 0, 0));
            drawTool.drawRectangle(MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), MainView.scale(getWidth()), MainView.scale(getHeight()));
            drawTool.setCurrentColor(new Color(0, 0, 255));
            drawTool.drawCircle(MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), MainView.scale(getRadius()));


            drawTool.setCurrentColor(new Color(255, 0, 255));
            drawTool.drawRectangle(MainView.translateAndScaleX(getX()), MainView.translateAndScaleY(getY()), MainView.scale(getWidth()), MainView.scale(getHeight()));
            drawTool.setCurrentColor(new Color(0, 255, 255));
            drawTool.drawCircle(MainView.translateAndScaleX(getX()), MainView.translateAndScaleY(getY()), MainView.scale(getRadius()));
        }
    }
    public double getDistanceTo(double x, double y){
        return Math.sqrt(Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2));
    }
}


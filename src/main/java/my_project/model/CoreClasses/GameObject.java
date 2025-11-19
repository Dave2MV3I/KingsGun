package my_project.model.CoreClasses;

import KAGO_framework.model.GraphicalObject;
import my_project.model.Graphics.Texture;

/**Blueprint Class for all In-Game Objects
 *<br><br>
 *Joshua Becker
 */
public class GameObject extends GraphicalObject {
    protected Texture texture;
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
        return Math.atan2(object.getY() - this.getX(),  object.getX() - this.getY());
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
    }
    /**
     * Moves the GameObject on the Y-axis
     *
     * @param my amount to be moved on the Y-axis
     */
    protected void moveY(double my) {
        y += my;
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
}

package my_project.modes.travelMode.Ammunition;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.modes.travelMode.Bandits.Bandit;
import my_project.view.MainView;

import java.awt.*;

public abstract class Ammunition extends GameObject {
    public Ammunition(double shots, double damage) {
        this.shots = shots;
        this.damage = damage;
        hitDrawableQueue = new Queue();
    }
    protected Color color;
    private double price;
    private Queue<HitDrawable> hitDrawableQueue;
    protected double shots;
    protected double damage;


    private double getPrice(){
        return price;
    }
    public double getShots(){
        return shots;
    }
    public boolean shoot(double direction, double startX, double startY, List<Bandit> bandits, int steps, double stepLength) {
        return shoot(direction, startX, startY, bandits, steps, stepLength, null);
    }

        public boolean shoot(double direction, double startX, double startY, List<Bandit> bandits, int steps, double stepLength, Bandit ignore) {
        if (shots > 0) {
            shots--;
            double currentX = startX;
            double currentY = startY;
            for (int i = 0; i < steps; i++) {
                bandits.toFirst();
                while (bandits.hasAccess()) {
                    if (bandits.getContent().collidesWith(currentX, currentY) && bandits.getContent() != ignore) {
                        hit(bandits.getContent(), bandits, currentX, currentY);
                        hitDrawableQueue.enqueue(new HitDrawable(startX, startY, currentX, currentY));
                        return true;
                    }
                    bandits.next();

                }
                currentX += Math.cos(direction) * stepLength;
                currentY += Math.sin(direction) * stepLength;
            }
            hitDrawableQueue.enqueue(new HitDrawable(startX, startY, currentX, currentY));
            return true;
        } else {
            System.out.println("no ammo");
            return false;
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        while (!hitDrawableQueue.isEmpty()) {

            hitDrawableQueue.front().draw(drawTool, this.color);
            hitDrawableQueue.dequeue();
        }
    }

    public abstract void hit(Bandit bandit, List<Bandit> bandits, double hitX, double hitY);
    private class HitDrawable {
        private double startX, startY;
        private double hitX, hitY;
        public HitDrawable(double startX, double startY, double hitX, double hitY) {
           this.startX = startX;
           this.startY = startY;
           this.hitX = hitX;
           this.hitY = hitY;
        }
        public void draw(DrawTool dt, Color color) {
            dt.setCurrentColor(color);
            dt.setLineWidth(16);
            dt.drawLine(MainView.translateAndScaleX(startX), MainView.translateAndScaleY(startY), MainView.translateAndScaleX(hitX), MainView.translateAndScaleY(hitY));
            dt.setCurrentColor(color);
            dt.drawFilledCircle(MainView.translateAndScaleX(hitX), MainView.translateAndScaleY(hitY), 16);
            dt.setCurrentColor(new Color(255, 255, 255));
            dt.drawFilledCircle(MainView.translateAndScaleX(hitX), MainView.translateAndScaleY(hitY), 12);
            dt.setCurrentColor(new Color(255, 255, 255));
            dt.setLineWidth(8);
            dt.drawLine(MainView.translateAndScaleX(startX), MainView.translateAndScaleY(startY), MainView.translateAndScaleX(hitX), MainView.translateAndScaleY(hitY));


        }
    }
}

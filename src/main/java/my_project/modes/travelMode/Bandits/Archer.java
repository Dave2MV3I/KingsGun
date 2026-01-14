package my_project.modes.travelMode.Bandits;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.model.Graphics.Texture;
import my_project.modes.travelMode.Carriage;
import my_project.view.MainView;

public class Archer extends Bandit {
    public double hP;
    private List<Arrow> arrows;
    private Carriage carriage;
    private double time;
    public Archer(Carriage carriage){
        arrows = new List<>();
        this.texture = new Texture("archer.png");
        this.carriage = carriage;
        hP = 500;
        x = 32;
        y = 500;
    }
    private class Arrow extends GameObject {
        public Carriage car;

        private double vX;
        private double vY;
        private double vel = 200;
        private boolean hasHit = false;

        private double abstandX;
        private double abstandY;
        private double abstand;

        public Arrow(double x, double y, Carriage carriage){

            this.x = x;
            this.y = y;
            this.radius = 2;

            this.car = carriage;

            abstandX = (car.getX()+32) - this.x;
            abstandY = (car.getY()+48) - this.y;

            abstand = Math.sqrt(Math.pow(abstandX, 2) + Math.pow(abstandY, 2));
            vX = (abstandX / abstand) * vel;
            vY = (abstandY / abstand) * vel;
        }
        @Override
        public void update(double dt){
            x = x + vX*dt;
            y = y + vY*dt;
            if (collidesWith(car) && !hasHit){
                car.loseHP(50);
                System.out.println("damage!!!!!!!!!!");
                hasHit = true;
            }
        }
        @Override
        public void draw(DrawTool drawTool){
            drawTool.setCurrentColor(255,255,255,255);
            drawTool.drawFilledCircle(MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), MainView.scale(2));
        }

    }

    public void draw(DrawTool drawtool){
        texture.autoDraw(drawtool, x, y, 32);
        super.draw(drawtool);
        arrows.toFirst();
        while (arrows.getContent() != null){
            arrows.getContent().draw(drawtool);
            arrows.next();
        }
    }
    @Override
    public void update(double dt){
        time += dt;
        if (time >= 5){
            shoot(x, y);
            System.out.println("shoot");
            time = 0;
        }

        arrows.toFirst();
        while (arrows.getContent() != null){
            arrows.getContent().update(dt);
            arrows.next();
        }
    }

    public void shoot(double xPos, double yPos){
        arrows.append(new Arrow(xPos, yPos, carriage));

    }
}

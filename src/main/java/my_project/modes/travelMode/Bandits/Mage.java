package my_project.modes.travelMode.Bandits;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.model.Graphics.SpriteSheet;
import my_project.modes.travelMode.Carriage;
import my_project.view.MainView;
/**By Mykhailo Badasian
 */
public class Mage extends Bandit {
    private List<Arrow> arrows;
    private Carriage carriage;
    private double time;
    public Mage(Carriage carriage, double y, int a, int b){
        arrows = new List<>();
        this.texture = new SpriteSheet("mage1.png", 1, 5);
        ((SpriteSheet) texture).setCurrent((int)(Math.random()*5), 0);
        this.carriage = carriage;
        this.width = texture.getWidth()/2;
        this.height = texture.getHeight();
        hP = 24;
        x = Math.random()*40 + 15 + a*155;
        this.y = y + b*(Math.random()*400);
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

            //abstand = Math.sqrt(Math.pow(abstandX, 2) + Math.pow(abstandY, 2));
            abstand = getDistanceTo(car);

            vX = (abstandX / abstand) * vel;
            vY = (abstandY / abstand) * vel;
        }
        @Override
        public void update(double dt){
            x = x + vX*dt;
            y = y + vY*dt;
            if (collidesWith(car) && !hasHit){
                car.loseHP(5);
                System.out.println("damage!!!!!!!!!!");
                hasHit = true;
                arrows.remove();
            }
            if (y>2200 || y<-400 || x>300 || x<0){
                arrows.remove();
            }
        }
        @Override
        public void draw(DrawTool drawTool){
            drawTool.setCurrentColor(255,255,255,255);
            drawTool.drawFilledCircle(MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), MainView.scale(2));
        }

    }

    public void draw(DrawTool drawtool){
        texture.autoDraw(drawtool, x - texture.getWidth()/4, y, 32);
        autoDrawHitbox(drawtool);
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
        if(getDistanceTo(carriage) < 300){
            arrows.append(new Arrow(xPos, yPos, carriage));
        }

    }
}

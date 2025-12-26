package my_project.modes.travelMode.Bandits;

import KAGO_framework.view.DrawTool;
import my_project.model.GameObject;
import my_project.model.Graphics.Texture;
import my_project.view.MainView;

public class Archer extends Bandit {
    public double hP;
    private Arrow[] arrows;
    public Archer(){
        arrows = new Arrow[8];
        this.texture = new Texture("archer.png");
        hP = 500;
        x = 32;
        y = 500;
    }
    private class Arrow extends GameObject {
        public Arrow(double x, double y){

            this.x = x;
            this.y = y;
        }
        @Override
        public void update(double dt){

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
        for (int i = 0; i < arrows.length; i++) {
            if (arrows[i] != null){
                arrows[i].draw(drawtool);
            }

        }
    }
    @Override
    public void update(double dt){
        if (Math.random()< 0.5){
            shoot(x, y);
        }
    }

    public void shoot(double xPos, double yPos){
        arrows[0] = new Arrow(xPos, yPos);

    }
}

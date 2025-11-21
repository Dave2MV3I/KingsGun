package my_project.model.TravelMode.Bandits;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.CoreClasses.GameObject;
import my_project.view.MainView;

public abstract class Bandit extends GameObject {
    public double hP;

    public void loseHP(double damage){
        hP = hP - damage;
    }

    @Override
    public void draw(DrawTool drawtool){
        showHP(drawtool);
    }

    public void showHP(DrawTool drawTool){
        drawTool.drawText(MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), "" + hP);
    }
}

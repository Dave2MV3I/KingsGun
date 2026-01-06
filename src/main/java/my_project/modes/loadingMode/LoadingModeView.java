package my_project.modes.loadingMode;

import KAGO_framework.view.DrawTool;
import javafx.animation.Animation;
import my_project.Config;
import my_project.model.Graphics.AnimatedSpriteSheet;
import my_project.modes.ModeView;

import java.awt.*;
import java.util.HashMap;

public class LoadingModeView extends ModeView<LoadingModeControl> {
    private ModeView loadedView;
    private AnimatedSpriteSheet transitionAnimation;
    private HashMap<String, AnimatedSpriteSheet> animations;
    public LoadingModeView(LoadingModeControl modeControl) {
        super(modeControl);
        animations = new HashMap<>();
        animations.put("standart", new AnimatedSpriteSheet("transitions/standart animation.png", 1, 5));
        animations.put("exit Dungeon", new AnimatedSpriteSheet("transitions/exit dungeon.png", 1, 35));
        animations.put("death", new AnimatedSpriteSheet("transitions/death.png", 1, 29));
        animations.put("open map", new AnimatedSpriteSheet("transitions/open map.png", 1, 35));
    }
    public void setLoadedView(ModeView loadedView) {
        this.loadedView = loadedView;
    }
    @Override
    public void draw(DrawTool drawTool) {
        double progressionFactor = (modeControl.getTimer() / modeControl.getTransitionTime());
        updateAnimation(progressionFactor);
        super.draw(drawTool);
        if (loadedView != null) {
            loadedView.draw(drawTool);
        }

        transitionAnimation.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);

        /*
        double loadingValue = progressionFactor < 0.5 ? (progressionFactor*2) : (1-((progressionFactor-0.5)*2));

        drawTool.setCurrentColor(new Color(0,0,0, (int)Math.min(loadingValue * 255,255)));
        drawTool.drawFilledRectangle(0,0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        drawTool.formatText("Arial", Font.BOLD, 100);
        if (loadedView != null) {
            drawTool.drawText(0, 100, "Loading " + modeControl.getLoadedControl().getClass().getSimpleName());
        }
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawFilledRectangle(50,Config.WINDOW_HEIGHT - 300,(Config.WINDOW_WIDTH-100) * progressionFactor, 50);
        */

    }

    public void setAnimation(String animation) {
        if (animations.containsKey(animation)) {
            System.out.println("Animation started: " + animation);
            transitionAnimation = animations.get(animation);
            transitionAnimation.setCurrent(0,0);
        } else {
            System.err.println("Animation " + animation + " not found");
        }
    }
    public void updateAnimation(double progress) {
        if (transitionAnimation != null) {
            int frameAmount = transitionAnimation.getCols();
            transitionAnimation.setCurrentX((int)(frameAmount * progress));
        }
    }
}

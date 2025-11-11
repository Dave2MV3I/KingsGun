package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.modeControl.MapModeControl;
import my_project.control.modeControl.ModeControl;

import java.awt.*;

public class MapModeView extends ModeView {
    private int amountVillages;
    private int[][] villagePos;

    public MapModeView(MapModeControl modeControl) {
        super(modeControl);
        //amountVillages = modeControl.getVillageControl().getAmountVillage();

    }
    public void setAmountVillages(int amountVillages) {
        System.out.println("Amount of villages: " + amountVillages);
        this.amountVillages = amountVillages;
        villagePos = new int[amountVillages][2];
        for(int i = 0; i < amountVillages; i++){
            villagePos[i][0] = Config.WINDOW_WIDTH/2 + (int)(Math.random()*100)-50;
            villagePos[i][1] = (int) (((Config.WINDOW_HEIGHT / 1.2) - (Config.WINDOW_HEIGHT / 6)) / (amountVillages+1)) * (i+1) + Config.WINDOW_HEIGHT/6;
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 0, 0));
        drawTool.drawFilledCircle(Config.WINDOW_WIDTH/2, Config.WINDOW_HEIGHT/6,10);
        drawTool.setCurrentColor(new Color(44, 44, 44));
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2-50, Config.WINDOW_HEIGHT/10,100,40);
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2-50, Config.WINDOW_HEIGHT/10-40,35,40);
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2+15, Config.WINDOW_HEIGHT/10-40,35,40);

        drawTool.setCurrentColor(new Color(54, 182, 11));
        for(int i = 0; i < amountVillages; i++) {
            drawTool.drawFilledCircle(villagePos[i][0], villagePos[i][1], 10);
        }

        drawTool.setCurrentColor(new Color(13, 152, 241));
        drawTool.drawFilledCircle(Config.WINDOW_WIDTH/2, Config.WINDOW_HEIGHT/1.2,10);
    }
}

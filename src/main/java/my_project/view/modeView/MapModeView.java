package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.modeControl.MapModeControl;
import my_project.control.modeControl.ModeControl;
import my_project.view.InputManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MapModeView extends ModeView {
    private int amountVillages;
    private int[][] villagePos;

    public MapModeView(MapModeControl modeControl) {
        super(modeControl);
        //amountVillages = modeControl.getVillageControl().getAmountVillage();

    }

    /**
     * Fills the 2D Array with the amount of Villages and their drawn x- and y-Pos.
     * @param amountVillages
     */
    public void setAmountVillages(int amountVillages) {
        System.out.println("Amount of villages: " + amountVillages);
        this.amountVillages = amountVillages;
        villagePos = new int[amountVillages+2][2];
        for(int i = 0; i < amountVillages+2; i++){
            if(i == 0 || i == amountVillages+1)  {
                villagePos[i][0] = Config.WINDOW_WIDTH / 2;
            }else {
                villagePos[i][0] = Config.WINDOW_WIDTH / 2 + (int) (Math.random() * 150) - 75;
            }
            villagePos[i][1] = (int) (((Config.WINDOW_HEIGHT / 1.2) - (Config.WINDOW_HEIGHT / 6)) / (amountVillages+1)) * (i) + Config.WINDOW_HEIGHT/6;
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(184, 111, 80));
        drawTool.drawFilledRectangle(0,0,Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT);

        for(int i = 0; i < villagePos.length - 1; i++) {
            drawTool.setCurrentColor(new Color(24, 20, 37));
            drawTool.setLineWidth(10);
            drawTool.drawLine(villagePos[i][0], villagePos[i][1], villagePos[i+1][0], villagePos[i+1][1]);
            drawTool.setCurrentColor(new Color(228, 166, 114));
            drawTool.setLineWidth(8);
            drawTool.drawLine(villagePos[i][0], villagePos[i][1], villagePos[i+1][0], villagePos[i+1][1]);
        }

        drawTool.setCurrentColor(new Color(255, 0, 0));
        drawTool.drawFilledCircle(Config.WINDOW_WIDTH/2, Config.WINDOW_HEIGHT/6,10);
        drawTool.setCurrentColor(new Color(38, 43, 68));
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2-50, Config.WINDOW_HEIGHT/10,100,40);
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2-50, Config.WINDOW_HEIGHT/10-40,35,40);
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2+15, Config.WINDOW_HEIGHT/10-40,35,40);

        drawTool.setCurrentColor(new Color(54, 182, 11));
        for(int i = 1; i < amountVillages+1; i++) {
            drawTool.drawFilledCircle(villagePos[i][0], villagePos[i][1], 10);
        }


        drawTool.setCurrentColor(new Color(13, 152, 241));
        drawTool.drawFilledCircle(Config.WINDOW_WIDTH/2, Config.WINDOW_HEIGHT/1.2,10);
    }

    //@Override
    public void update(double dt) {

    }

    public void manageMouseInput(MouseEvent e) {

    }
}

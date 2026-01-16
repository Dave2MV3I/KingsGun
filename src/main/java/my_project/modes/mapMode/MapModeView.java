package my_project.modes.mapMode;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.modes.ModeView;
import my_project.modes.villageMode.Village;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MapModeView extends ModeView<MapModeControl> {
    private int amountVillages;
    private List<Village> villages;
    private int[][] villagePos;
    private Texture mapTexture;
    private Texture mapSymbolVillage;
    private Texture mapSymbolCastle;
    private int currentVillage = 0;
    private double radius = 15;
    private boolean inMoving = false;

    public MapModeView(MapModeControl modeControl) {
        super(modeControl);
        //amountVillages = modeControl.getVillageControl().getAmountVillage();
        mapTexture = new Texture("Map.png");
        mapSymbolVillage = new Texture("MapSymbolVillage.png");
        mapSymbolCastle = new Texture("MapSymbolCastle.png");
    }

    /**
     * Fills the 2D Array with the amount of Villages and their drawn x- and y-Pos.
     * @param amountVillages
     */
    public void setAmountVillages(int amountVillages) {
        if(this.amountVillages == 0) {
            this.amountVillages = amountVillages;
            villagePos = new int[amountVillages + 2][2];
            villages.toFirst();
            for (int i = 0; i < amountVillages + 2; i++) {
                if (i == 0 || i == amountVillages + 1) {
                    villagePos[i][0] = Config.WINDOW_WIDTH / 2;
                    villagePos[i][1] = (int) (Config.WINDOW_HEIGHT / 1.2 - ((Config.WINDOW_HEIGHT / 1.2) - (Config.WINDOW_HEIGHT / 6)) / (amountVillages + 1) * (i));
                } else {
                    villagePos[i][0] = villages.getContent().getMapX();
                    villagePos[i][1] = villages.getContent().getMapY();
                    villages.next();
                }
            }
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        mapTexture.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);

        //Draw Roadlines
        for(int i = 0; i < villagePos.length - 1; i++) {
            drawTool.setCurrentColor(new Color(115, 62, 57));
            drawTool.setLineWidth(21);
            drawTool.drawLine(villagePos[i][0], villagePos[i][1], villagePos[i+1][0], villagePos[i+1][1]);
            drawTool.setCurrentColor(new Color(194, 133, 105));
            drawTool.setLineWidth(7);
            drawTool.drawLine(villagePos[i][0], villagePos[i][1], villagePos[i+1][0], villagePos[i+1][1]);
        }

        //Draw Castle
        mapSymbolCastle.drawToWidth(drawTool, Config.WINDOW_WIDTH/2 -65, 50, 130);
        /*drawTool.setCurrentColor(new Color(194, 133, 105));
        drawTool.drawFilledCircle(Config.WINDOW_WIDTH/2, Config.WINDOW_HEIGHT/6,15);
        drawTool.setCurrentColor(new Color(38, 43, 68));
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2-50, Config.WINDOW_HEIGHT/10,100,40);
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2-50, Config.WINDOW_HEIGHT/10-40,35,40);
        drawTool.drawFilledRectangle(Config.WINDOW_WIDTH/2+15, Config.WINDOW_HEIGHT/10-40,35,40);*/

        drawTool.setLineWidth(4);
        for(int i = 1; i <= amountVillages+1; i++) {
            drawTool.setCurrentColor(new Color(194, 133, 105));
            if(i < currentVillage+1) {
                drawTool.setCurrentColor(new Color(234, 212, 170));
            }
            drawTool.drawFilledCircle(villagePos[i][0], villagePos[i][1], 15);

            if(i == currentVillage+1) {
                drawTool.setCurrentColor(new Color(234, 212, 170));
                drawTool.drawCircle(villagePos[i][0], villagePos[i][1], radius);
            }
            if(i != amountVillages+1) {
                if (villagePos[i][0] > Config.WINDOW_WIDTH / 2) {
                    mapSymbolVillage.drawToWidth(drawTool, villagePos[i][0] + 20, villagePos[i][1] - 20, 70);
                } else {
                    mapSymbolVillage.drawToWidth(drawTool, villagePos[i][0] - 90, villagePos[i][1] - 20, 70);
                }
            }
        }

        drawTool.setCurrentColor(new Color(234, 212, 170));
        drawTool.drawFilledCircle(Config.WINDOW_WIDTH/2, Config.WINDOW_HEIGHT/1.2,15);
    }

    public void update(double dt) {
        if(radius < 15) {
            inMoving = false;
        }else if(radius > 25) {
            inMoving = true;
        }
        if(inMoving) {
            radius -= 20*dt;
        }else {
            radius += 15*dt;
        }
    }

    public void manageMouseInput(MouseEvent e) {
        System.out.println("e ist da");
        for(int i = 1; i < amountVillages+2; i++) {
            if (Math.sqrt(Math.pow(e.getX()-villagePos[i][0],2)+Math.pow(e.getY()-villagePos[i][1],2)) < 30){
                if(i == amountVillages+1) {
                    System.out.println("Castle");
                } else {
                    System.out.println("Village " + i + " ist da");
                }
                if(i == currentVillage+1) {
                    modeControl.getMainController().loadMode("travel", "close map");
                    currentVillage++;
                    if(i == amountVillages+1) {
                        modeControl.getMainController().loadMode("castle", "close map");
                    }
                }
            }
        }
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }
}

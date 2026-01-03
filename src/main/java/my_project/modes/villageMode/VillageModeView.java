package my_project.modes.villageMode;

import KAGO_framework.view.DrawTool;

import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.modes.ModeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class VillageModeView extends ModeView<VillageModeControl> {
    private Village currentVillage;
    private JFrame shopFrame;
    private Texture villageBackground;

    public VillageModeView(VillageModeControl modeControl) {
        super(modeControl);
        villageBackground = new Texture("VillageBackground.png");
    }

    public void activationVillage(Village village) {
        currentVillage = village;
        ShopWindow shopWindow = new ShopWindow(modeControl.getMainController());
        shopFrame = modeControl.getMainController().createJFrame(shopWindow.getShopPane(), 400, 250, false);
        shopFrame.setLocation(570, 200);
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        villageBackground.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
        //drawTool.drawFilledRectangle(570, 200, 300, 300);
    }

    public void manageMouseInput(MouseEvent e) {
        if (e.getX() > 570 && e.getX() < 870 && e.getY() > 200 && e.getY() < 500) {
            shopFrame.setVisible(true);
        }
        if (e.getX() > 0 && e.getX() < 300 && e.getY() > 500 && e.getY() < 900) {
            if(!shopFrame.isVisible()) {
                modeControl.getMainController().loadMode("map");
            }
        }
        if (e.getX() > Config.WINDOW_WIDTH-300 && e.getX() < Config.WINDOW_WIDTH && e.getY() > 500 && e.getY() < 900) {
            if(!shopFrame.isVisible()) {
                modeControl.getMainController().loadMode("dungeon");
            }
        }
    }
}

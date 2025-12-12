package my_project.modes.villageMode;

import KAGO_framework.view.DrawTool;

import my_project.modes.ModeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class VillageModeView extends ModeView<VillageModeControl> {
    private Village currentVillage;
    private JFrame shopFrame;

    public VillageModeView(VillageModeControl modeControl) {
        super(modeControl);

    }

    public void activationVillage(Village village) {
        currentVillage = village;
        ShopWindow shopWindow = new ShopWindow();
        shopFrame = modeControl.getMainController().createJFrame(shopWindow.getShopPane(), 400, 200, false);
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        drawTool.drawFilledRectangle(300, 300, 300, 300);
    }

    public void manageMouseInput(MouseEvent e) {
        if (e.getX() > 300 && e.getX() < 600 && e.getY() > 300 && e.getY() < 600) {
            shopFrame.setVisible(true);
        }
        if (e.getX() > 300 && e.getX() < 600 && e.getY() > 300 && e.getY() < 600) {

        }
    }
}

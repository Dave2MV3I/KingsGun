package my_project.modes.villageMode;

import KAGO_framework.view.DrawTool;

import my_project.modes.ModeView;

import javax.swing.*;
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

    }

    public void manageMouseInput(MouseEvent e) {
        shopFrame.setVisible(true);
    }
}

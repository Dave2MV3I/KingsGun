package my_project.modes.villageMode;

import KAGO_framework.model.Sound;
import KAGO_framework.view.DrawTool;

import javafx.embed.swing.JFXPanel;
import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.modes.ModeView;
import my_project.settings.SettingsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class VillageModeView extends ModeView<VillageModeControl> {
    private JFrame shopFrame;
    private Texture villageBackground;
    private JFXPanel fxPanel = new JFXPanel();
    private Sound openMap = new Sound("src/main/resources/sound/OpenMap.mp3","Open Map", false);
    private Sound openDungeon = new Sound("src/main/resources/sound/OpenDungeon.mp3","Enter Dungeon", false);
    private Sound openShop = new Sound("src/main/resources/sound/Buying.mp3","Open Shop", false);

    public VillageModeView(VillageModeControl modeControl) {
        super(modeControl);
        villageBackground = new Texture("VillageBackground.png");
    }

    public void activationVillage() {
        ShopWindow shopWindow = new ShopWindow(modeControl.getMainController());
        shopFrame = modeControl.getMainController().createJFrame(shopWindow.getShopPane(), 400, 350, false);
        shopFrame.setLocation(570, 200);
    }

    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(255, 255, 255));
        villageBackground.drawToWidth(drawTool, 0, 0, Config.WINDOW_WIDTH);
    }

    public void manageMouseInput(MouseEvent e) {
        if (e.getX() > 570 && e.getX() < 870 && e.getY() > 200 && e.getY() < 500) {
            shopFrame.setVisible(true);
            if(openShop.isPlaying()) openShop.stop();
            openShop.setVolume(SettingsModel.getSoundVolume());
            openShop.play();
        }
        if (e.getX() > 0 && e.getX() < 300 && e.getY() > 500 && e.getY() < 900) {
            if(!shopFrame.isVisible()) {
                modeControl.getMainController().loadMode("map", "open map");
                if(openMap.isPlaying()) openMap.stop();
                openMap.setVolume(SettingsModel.getSoundVolume());
                openMap.play();
            }
        }
        if (e.getX() > Config.WINDOW_WIDTH-300 && e.getX() < Config.WINDOW_WIDTH && e.getY() > 500 && e.getY() < 900) {
            if(!shopFrame.isVisible()) {
                modeControl.getMainController().loadMode("dungeon", "enter dungeon");
                if(openDungeon.isPlaying()) openDungeon.stop();
                openDungeon.setVolume(SettingsModel.getSoundVolume());
                openDungeon.play();
            }
        }
    }
}

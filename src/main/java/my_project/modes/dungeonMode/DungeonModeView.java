package my_project.modes.dungeonMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.modes.dungeonMode.Attacks.AttackRepresentation;
import my_project.modes.dungeonMode.Tiles.Tile;
import my_project.modes.ModeView;

import java.awt.*;

public class DungeonModeView extends ModeView<DungeonModeControl> {
    public DungeonModeView(DungeonModeControl modeControl) {
        super(modeControl);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(58, 68, 102));
        drawTool.drawFilledRectangle(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        drawDungeon(drawTool);
        drawTool.setCurrentColor(new Color(18, 172, 0));
        modeControl.getDungeonPlayer().drawHealth(drawTool, 20, 20, Config.WINDOW_WIDTH/3, 50);
        modeControl.getDungeonPlayer().drawMoney(drawTool, 20, 85);
        drawTool.setCurrentColor(new Color(255, 182, 0));

        modeControl.getDungeonPlayer().drawExit(drawTool, 0, Config.WINDOW_HEIGHT/2);
        super.draw(drawTool);
        drawTool.formatText("Arial", Font.PLAIN, Config.WINDOW_HEIGHT/10);
    }
    private void drawDungeon(DrawTool drawTool) {
        for (Tile[] tiles : modeControl.getDungeonTiles()) {
            for (Tile tile : tiles) {
                if (tile != null) tile.draw(drawTool);

            }
        }

        if (modeControl.getMonster() != null && modeControl.getDungeonPlayer() != null) {

            if (modeControl.getMonster().getY() < modeControl.getDungeonPlayer().getY()) { // Monster above DungeonPlayer
                modeControl.getMonster().draw(drawTool);
                modeControl.getDungeonPlayer().draw(drawTool);
                //System.out.println("Monster above DungeonPlayer");
            } else {// DungeonPlayer above Monster
                modeControl.getDungeonPlayer().draw(drawTool);
                modeControl.getMonster().draw(drawTool);
                //System.out.println("DungeonPlayer above Monster");
            }

        }

        for (AttackRepresentation aR: modeControl.getDungeon().getCurrentAttacks()) draw(drawTool);
    }
}

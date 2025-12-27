package my_project.modes.dungeonMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
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
        super.draw(drawTool);
    }
    private void drawDungeon(DrawTool drawTool) {
        for (Tile[] tiles : modeControl.getDungeonTiles()) {
            for (Tile tile : tiles) {
                if (tile != null) tile.draw(drawTool);

            }
        }

        if (modeControl.getMonster() != null && modeControl.getPlayer() != null) {

            if (modeControl.getMonster().getY() < modeControl.getPlayer().getY()) { // Monster above Player
                modeControl.getMonster().draw(drawTool);
                modeControl.getPlayer().draw(drawTool);
                //System.out.println("Monster above Player");
            } else {// Player above Monster
                modeControl.getPlayer().draw(drawTool);
                modeControl.getMonster().draw(drawTool);
                //System.out.println("Player above Monster");
            }

        }
    }
}

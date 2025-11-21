package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.control.modeControl.DungeonModeControl;
import my_project.control.modeControl.ModeControl;
import my_project.model.DungeonMode.Tiles.Tile;

import java.awt.*;

public class DungeonModeView extends ModeView {
    public DungeonModeView(DungeonModeControl modeControl) {
        super(modeControl);
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(new Color(58, 68, 102));
        drawTool.drawFilledRectangle(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        drawDungeon(drawTool);
        super.draw(drawTool);
        ((DungeonModeControl)modeControl).getDungeon().getMonster().draw(drawTool);
    }
    private void drawDungeon(DrawTool drawTool) {
        for (Tile[] tiles : ((DungeonModeControl)modeControl).getDungeonTiles()) {
            for (Tile tile : tiles) {
                if (tile != null) tile.draw(drawTool);

            }
        }
        ((DungeonModeControl) modeControl).getPlayer().draw(drawTool);
    }
}

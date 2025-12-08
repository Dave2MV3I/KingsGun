package my_project.Modes.DungeonMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.Modes.DungeonMode.Tiles.Tile;
import my_project.Modes.ModeView;

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
        modeControl.getDungeon().getMonster().draw(drawTool);
    }
    private void drawDungeon(DrawTool drawTool) {
        for (Tile[] tiles : modeControl.getDungeonTiles()) {
            for (Tile tile : tiles) {
                if (tile != null) tile.draw(drawTool);

            }
        }
        modeControl.getMonster().draw(drawTool);
        modeControl.getPlayer().draw(drawTool);
    }
}

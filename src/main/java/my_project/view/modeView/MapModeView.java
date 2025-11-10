package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.control.modeControl.MapModeControl;
import my_project.control.modeControl.ModeControl;

import java.awt.*;

public class MapModeView extends ModeView {
    private int amountVillages;

    public MapModeView(MapModeControl modeControl) {
        super(modeControl);
        amountVillages = modeControl.getVillageControl().getAmountVillage();
    }

    @Override
    public void draw(DrawTool drawTool) {

    }
}

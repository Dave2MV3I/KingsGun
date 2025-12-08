package my_project.Modes.VillageMode;

import KAGO_framework.view.DrawTool;
import my_project.Modes.ModeView;

import java.awt.event.MouseEvent;

public class VillageModeView extends ModeView<VillageModeControl> {
    private Village currentVillage;

    public VillageModeView(VillageModeControl modeControl) {
        super(modeControl);
    }

    public void activationVillage(Village village) {
        currentVillage = village;
    }

    public void draw(DrawTool drawTool) {

    }

    public void manageMouseInput(MouseEvent e) {

    }
}

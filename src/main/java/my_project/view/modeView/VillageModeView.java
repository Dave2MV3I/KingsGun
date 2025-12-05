package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.control.modeControl.ModeControl;
import my_project.control.modeControl.VillageModeControl;
import my_project.model.VillageMode.Village;

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

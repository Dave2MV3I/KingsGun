package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.control.modeControl.ModeControl;

public abstract class ModeView {
    protected ModeControl modeControl;
    public ModeView(ModeControl modeControl) {
        this.modeControl = modeControl;
        modeControl.setModeView(this);
    }
    public void draw(DrawTool drawTool) {
        drawTool.drawText(0,12,getClass().getSimpleName() + " currently rendering");
        drawTool.drawText(0,24,modeControl.getClass().getSimpleName() + " currently controlling");
    }
}

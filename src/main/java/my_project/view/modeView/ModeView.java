package my_project.view.modeView;

import KAGO_framework.view.DrawTool;
import my_project.control.modeControl.ModeControl;
/**Blueprint Class for all Mode Views
 *<br><br>
 *Joshua Becker
 */
public abstract class ModeView<MC extends ModeControl> {
    protected MC modeControl;
    public ModeView(MC modeControl) {
        this.modeControl = modeControl;
        modeControl.setModeView(this);
    }
    public void draw(DrawTool drawTool) {
        drawTool.drawText(0,12,getClass().getSimpleName() + " currently rendering");
        drawTool.drawText(0,24,modeControl.getClass().getSimpleName() + " currently controlling");
    }
}

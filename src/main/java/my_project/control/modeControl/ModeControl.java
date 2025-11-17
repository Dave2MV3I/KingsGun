package my_project.control.modeControl;

import KAGO_framework.view.DrawTool;
import my_project.control.MainController;
import my_project.view.modeView.ModeView;
/**Blueprint Class for all Mode Controls
 *<br><br>
 *Joshua Becker
 */
public abstract class ModeControl{
    protected MainController mainController;
    protected ModeView modeView;
    boolean active;
    protected double timer;

    public ModeControl() {
        timer = 0.0;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Links the Controller to a View of a corresponding gamemode
     * DO NOT LINK CONTROLLERS AND VIEW THAT ARE NOT OF THE SAME GAMEMODE!
     */
    public void setModeView(ModeView modeView){
        this.modeView = modeView;
    }
    public ModeView getModeView(){return modeView;}
    public void update(double dt){
        timer += dt;
    }

    /**
     * Notifies the ModeController, that its Activation status has been Changed.
     * @param active wether the ModeController should be activated or deactivated
     */
    public void setActive(boolean active){
        this.active = active;
        if(active) {
            activate();
            timer = 0.0;
        } else{
            deactivate();
        }
    }

    /** Method to be executed whenever the ModeController is activated
     */
    protected abstract void activate();
    /** Method to be executed whenever the ModeController is deactivated
     */
    protected abstract void deactivate();
}

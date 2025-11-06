package my_project.control.modeControl;

import my_project.view.modeView.ModeView;

public abstract class ModeControl{
    boolean active;
    ModeView modeView;
    public ModeControl() {

    }

    /**
     * Links the Controller to a View of a corresponding gamemode
     * DO NOT LINK CONTROLLERS AND VIEW THAT ARE NOT OF THE SAME GAMEMODE!
     */
    public void setModeView(ModeView modeView){
        this.modeView = modeView;
    }
    public void update(double dt){

    }

    /**
     * Notifies the ModeController, that its Activation status has been Changed.
     * @param active wether the ModeController should be activated or deactivated
     */
    public void setActive(boolean active){
        this.active = active;
    }
}

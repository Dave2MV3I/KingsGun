package my_project.control.modeControl;

import my_project.view.modeView.ModeView;

public abstract class ModeControl{
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
}

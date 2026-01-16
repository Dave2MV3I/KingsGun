package my_project.settings;
import my_project.control.MainController;

import javax.swing.*;

/**
 * This class coordinates communication between SettingsModel and SettingsWindow (the GUI)
 * <br><br>
 * David Glusmann
 */
public class SettingsController {

    // Attributes
    private MainController mainController;
    private SettingsModel settings = new SettingsModel();
    private JFrame settingsFrame;

    // Refereces

    // Data structures

    // Constructor
    public SettingsController(MainController mainController){
        this.mainController = mainController;
        SettingsWindow settingsWindow = new SettingsWindow(this);
        settingsFrame = mainController.createJFrame(settingsWindow.getMainPanel(), 400, 200, false);
    }

    /**
     * Method for changing visual and auditory game settings with float values.
     * @param setting options: musicVolume, soundVolume, brightness
     * @param value the new value to be assigned
     */
    public void setSetting(String setting, float value) {
        switch (setting) {
            case "musicVolume" : SettingsModel.setMusicVolume(value);
                break;
            case "soundVolume" : SettingsModel.setSoundVolume(value);
                break;
            case "brightness" : SettingsModel.setBrightness(value);
                break;
            case "performance" : SettingsModel.setPerformance(value);
        }
    }

    // public void setSetting(String setting, String value) {}

    // public void setSetting(String setting, boolean value) {}

    public JFrame getSettingsFrame() {return settingsFrame;}}

package my_project.view.javafx;
import my_project.model.CoreClasses.SettingsController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * SettingsWindow is a View Class for the Settings GUI.
 * <br><br>
 * David Glusmann
 */
public class SettingsWindow {
    private JPanel mainPanel;
    private JLabel header;
    private JSlider musicSlider; // Divide with 100
    private JSlider soundSlider; // Divide with 100
    private JSlider brightnessSlider; // Divide with 100
    private JLabel musicLabel;
    private JLabel soundLabel;
    private JLabel brightnessLabel;

    private SettingsController settingController;

    /**
     * Constructor adds Change Listeners to the swing elements.
     */
    public SettingsWindow(SettingsController settingController) {
        this.settingController = settingController;

        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                settingController.setSetting("musicVolume", musicSlider.getValue());}
        });

        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                settingController.setSetting("soundVolume", soundSlider.getValue());
            }
        });

        brightnessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                settingController.setSetting("brightness", brightnessSlider.getValue());
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}
}

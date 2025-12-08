package my_project.settings;

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
    private JSlider musicSlider;
    private JSlider soundSlider;
    private JSlider brightnessSlider;
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
                settingController.setSetting("musicVolume", (float) musicSlider.getValue() /100);}
        });

        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                settingController.setSetting("soundVolume", (float) soundSlider.getValue() /100);
            }
        });

        brightnessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                settingController.setSetting("brightness", (float) brightnessSlider.getValue() /100);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}
}

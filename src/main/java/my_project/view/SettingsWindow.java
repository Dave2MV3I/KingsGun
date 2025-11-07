package my_project.view;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * SettingsWindow is a View Class for the Settings GUI. <br>
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

    private MainView mainView;

    /**
     * Constructor adds Change Listeners to the swing elements.
     */
    public SettingsWindow(MainView mainView) {
        this.mainView = mainView;

        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                mainView.setSetting("musicVolume", musicSlider.getValue());
            }
        });

        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                mainView.setSetting("soundVolume", soundSlider.getValue());
            }
        });

        brightnessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                mainView.setSetting("brightness", brightnessSlider.getValue());
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}
}

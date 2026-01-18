package my_project.view.javafx;

import my_project.control.MainController;
import my_project.settings.SettingsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**By Joshua Becker
 */
public class BackendDeveloperAcces {
    private JPanel panel1;
    private JLabel modeDisplay;
    private JButton setModeButton;
    private JTextField modeSelection;
    private JLabel fpsDisplay;
    private JComboBox comboBox1;
    private JButton EXITPROGRAMMButton;
    private JCheckBox debuggingCheckBox;

    public BackendDeveloperAcces(MainController controller) {
        modeDisplay.setText(controller.getMode());
        setModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setMode(modeSelection.getText());
                modeDisplay.setText(controller.getMode());
            }
        });
        modeSelection.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    controller.setMode(modeSelection.getText());
                    modeDisplay.setText(controller.getMode());
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadMode(comboBox1.getSelectedItem().toString());
                modeDisplay.setText(controller.getMode());
            }
        });
        EXITPROGRAMMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        debuggingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsModel.setDebugging(debuggingCheckBox.isSelected());
            }
        });
    }

    public Container getContentPane() {
        return panel1;
    }

    public void setFPS(double fps) {
        fpsDisplay.setText(String.valueOf(Math.floor(fps*10)/10));
    }
}

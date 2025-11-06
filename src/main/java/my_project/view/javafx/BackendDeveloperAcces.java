package my_project.view.javafx;

import my_project.control.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackendDeveloperAcces {
    private JPanel panel1;
    private JLabel modeDisplay;
    private JButton setModeButton;
    private JTextField modeSelection;

    public BackendDeveloperAcces(MainController controller) {
        modeDisplay.setText(controller.getMode());
        setModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setMode(modeSelection.getText());
                modeDisplay.setText(controller.getMode());
            }
        });
    }

    public Container getContentPane() {
        return panel1;
    }
}

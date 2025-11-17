package my_project.view;

import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.MainController;
import my_project.control.ProgramController;
import my_project.control.modeControl.MapModeControl;
import my_project.view.modeView.MapModeView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft.
 */
public class InputManager extends InteractiveGraphicalObject {

    private final MainController mainController;

    /**
     * Objekterzeugung
     * @param mainController Nötig als Objekt vom Controllerbereich, das informiert wird
     */
    public InputManager(MainController mainController){
        this.mainController = mainController;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //if(e.getButton() == MouseEvent.BUTTON1 && mainController.getMode() == "map"){
            //mainController.getModeControls().get("map").getModeView.manageMouseInput(e);}
    }

    @Override
    public void keyPressed (int key){
        switch (key){
            case KeyEvent.VK_ESCAPE : mainController.processInput("settings");
            break;
            case KeyEvent.VK_O : mainController.processInput("zoomOut");
            break;
            case KeyEvent.VK_I : mainController.processInput("zoomIn");
            break;
            case KeyEvent.VK_P : mainController.processInput("centerCamera");
        }
    }

}

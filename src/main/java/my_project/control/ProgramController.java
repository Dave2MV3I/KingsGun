package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.Sound;
import javafx.embed.swing.JFXPanel;
import my_project.view.InputManager;
import my_project.view.Mouse;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern.
 * Hinweise:
 * - Der Konstruktor sollte nicht geändert werden.
 * - Sowohl die startProgram()- als auch die updateProgram(...)-Methoden müssen vorhanden sein und ihre Signatur sollte
 *   nicht geändert werden
 * - Zusätzliche Methoden sind natürlich gar kein Problem
 */
public class ProgramController {

    //Attribute


    // Referenzen
    private final ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.
    private final MainController mainController = new MainController();
    private final InputManager inputManager = new InputManager(mainController);
    private final Mouse mouse = new Mouse();

    private JFXPanel fxPanel = new JFXPanel();
    private Sound music = new Sound("src/main/resources/sound/MainTheme.mp3","MainTheme", true);
    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen. Hier sollte also alles geregelt werden,
     * was zu diesem Zeipunkt passieren muss.
     */
    public void startProgram() {
        music.play();
        music.setVolume(0.2);

        // Teile dem ViewController-Objekt mit, dass das Objekt gezeichnet werden soll
        viewController.draw(mainController);
        viewController.register(inputManager);
        viewController.register(mouse);
    }

    /**
     * Diese Methode wird vom ViewController-Objekt automatisch mit jedem Frame aufgerufen (ca. 60mal pro Sekunde)
     * @param dt Zeit seit letztem Frame in Sekunden
     */
    public void updateProgram(double dt){

    }
}

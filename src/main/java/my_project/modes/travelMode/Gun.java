package my_project.modes.travelMode;


import my_project.view.InputManager;
import my_project.view.Mouse;

import java.awt.event.KeyEvent;

public class Gun {
    private Carriage carriage;
    public Gun(Carriage carriage) {
        this.carriage = carriage;
    }
    public void update(double dt) {
        if (InputManager.isPressed(KeyEvent.VK_SPACE) || Mouse.isDown(1)) {
            if (carriage.getAmmo().top() != null) {
                System.out.println(carriage.getAmmo().top().getClass().getSimpleName());
                carriage.getAmmo().pop();
            }else {
                System.out.println("blyat");
            }
        }
    }
}

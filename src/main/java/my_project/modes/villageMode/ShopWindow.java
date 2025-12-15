package my_project.modes.villageMode;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.modes.travelMode.Ammunition.Ammunition;
import my_project.modes.travelMode.Ammunition.ElectricAmmunition;
import my_project.modes.travelMode.Ammunition.ExplosiveAmmunition;
import my_project.modes.travelMode.Ammunition.NormalAmmunition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopWindow {
    private Queue<Ammunition> shopItems;

    private JPanel shopPanal;
    private JButton discardButton;
    private JButton takeButton;
    private JLabel shopOption;

    public ShopWindow() {
        initiateShopQueue();
        updateShopText();
        ImageIcon imageIcon = new ImageIcon("src/main/resources/graphic/NormalAmmo.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(24*3, 32*3, Image.SCALE_DEFAULT));
        shopOption.setIcon(imageIcon);

        //Image image = imageIcon.getImage();
        //shopPanal.getGraphics().drawImage(image,100,100,100,100,null);

        discardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopItems.dequeue();
                addAmmunition();
                updateShopText();
            }
        });
        takeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ammunition takenAmmunition = shopItems.front();
                shopItems.dequeue();
                addAmmunition();
                updateShopText();
            }
        });
    }

    private void updateShopText() {
        String ammoType = "";
        if (shopItems.front() instanceof NormalAmmunition) {
            ammoType = "Normal Ammunition";
        }else if (shopItems.front() instanceof ElectricAmmunition) {
            ammoType = "Electric Ammunition";
        }else if (shopItems.front() instanceof ExplosiveAmmunition) {
            ammoType = "Explosive Ammunition";
        }
        shopOption.setText(ammoType);
    }

    private void addAmmunition() {
        double r = Math.random();
        Ammunition newAmmo;
        if (r < 0.5) {
            newAmmo = new NormalAmmunition();
        }else if (r < 0.75) {
            newAmmo = new ElectricAmmunition();
        }else {
            newAmmo = new ExplosiveAmmunition();
        }
        shopItems.enqueue(newAmmo);
    }

    private void initiateShopQueue() {
        shopItems = new Queue<Ammunition>();
        for (int i = 0; i < 10; i++) {
            addAmmunition();
        }
    }

    public JPanel getShopPane() {return shopPanal;}
}

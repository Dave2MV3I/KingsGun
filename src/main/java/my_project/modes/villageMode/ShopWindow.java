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

    private ImageIcon normalIcon = new ImageIcon("src/main/resources/graphic/NormalAmmo.png");
    private ImageIcon electricIcon = new ImageIcon("src/main/resources/graphic/ElektricAmmo.png");
    private ImageIcon explodingIcon = new ImageIcon("src/main/resources/graphic/ExplodingAmmo.png");

    public ShopWindow() {
        initiateShopQueue();
        updateShopText();

        normalIcon.setImage(normalIcon.getImage().getScaledInstance(24*3, 32*3, Image.SCALE_DEFAULT));
        electricIcon.setImage(electricIcon.getImage().getScaledInstance(24*3, 32*3, Image.SCALE_DEFAULT));
        explodingIcon.setImage(explodingIcon.getImage().getScaledInstance(24*3, 32*3, Image.SCALE_DEFAULT));

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
            shopOption.setIcon(normalIcon);
        }else if (shopItems.front() instanceof ElectricAmmunition) {
            ammoType = "Electric Ammunition";
            shopOption.setIcon(electricIcon);
        }else if (shopItems.front() instanceof ExplosiveAmmunition) {
            ammoType = "Explosive Ammunition";
            shopOption.setIcon(explodingIcon);
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

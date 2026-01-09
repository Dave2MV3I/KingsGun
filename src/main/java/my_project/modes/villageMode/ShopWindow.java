package my_project.modes.villageMode;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.control.MainController;
import my_project.model.Player;
import my_project.modes.travelMode.Ammunition.Ammunition;
import my_project.modes.travelMode.Ammunition.ElectricAmmunition;
import my_project.modes.travelMode.Ammunition.ExplosiveAmmunition;
import my_project.modes.travelMode.Ammunition.NormalAmmunition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopWindow {
    private MainController mainController;

    private Queue<Ammunition> shopItems;

    private JPanel shopPanal;
    private JButton discardButton;
    private JButton takeButton;
    private JLabel shopOption;
    private JLabel optionPicture;
    private JLabel priceLabel;
    private JLabel tippLabel;
    private JLabel playerMoney;

    private ImageIcon normalIcon = new ImageIcon("src/main/resources/graphic/NormalAmmo.png");
    private ImageIcon electricIcon = new ImageIcon("src/main/resources/graphic/ElektricAmmo.png");
    private ImageIcon explodingIcon = new ImageIcon("src/main/resources/graphic/ExplodingAmmo.png");

    private ImageIcon coinIcon = new ImageIcon("src/main/resources/graphic/Coin.png");

    public ShopWindow(MainController mainController) {
        this.mainController = mainController;

        initiateShopQueue();
        updateShopText();

        priceLabel.setIcon(coinIcon);

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
                if(checkingToBuy(shopItems.front())) {
                    Ammunition takenAmmunition = shopItems.front();
                    shopItems.dequeue();
                    addAmmunition();
                    updateShopText();
                }
            }
        });
    }

    private boolean checkingToBuy(Ammunition ammo) {
        if(ammo instanceof NormalAmmunition) {
            if(mainController.getCurrentPlayer().getMoney() >= 10) {
                mainController.getCurrentPlayer().addMoney(-10);
                return true;
            }else showTipp();
        }else if(ammo instanceof ElectricAmmunition) {
            if(mainController.getCurrentPlayer().getMoney() >= 12) {
                mainController.getCurrentPlayer().addMoney(-12);
                return true;
            }else showTipp();
        }else if(ammo instanceof ExplosiveAmmunition) {
            if(mainController.getCurrentPlayer().getMoney() >= 15) {
                mainController.getCurrentPlayer().addMoney(-15);
                return true;
            }else showTipp();
        }
        return false;
    }

    private void showTipp() {
        tippLabel.setText("Tipp: Go to the Dungeon to get Money");
    }

    private void updateShopText() {
        String ammoType = "";
        if (shopItems.front() instanceof NormalAmmunition) {
            ammoType = "Normal Ammunition";
            optionPicture.setIcon(normalIcon);
            priceLabel.setText("10");
        }else if (shopItems.front() instanceof ElectricAmmunition) {
            ammoType = "Electric Ammunition";
            optionPicture.setIcon(electricIcon);
            priceLabel.setText("12");
        }else if (shopItems.front() instanceof ExplosiveAmmunition) {
            ammoType = "Explosive Ammunition";
            optionPicture.setIcon(explodingIcon);
            priceLabel.setText("15");
        }
        shopOption.setText(ammoType);
        playerMoney.setIcon(coinIcon);
        playerMoney.setText(String.valueOf(mainController.getCurrentPlayer().getMoney()));
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

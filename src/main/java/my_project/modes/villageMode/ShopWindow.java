package my_project.modes.villageMode;

import KAGO_framework.model.Sound;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import javafx.embed.swing.JFXPanel;
import my_project.control.MainController;
import my_project.modes.travelMode.Ammunition.Ammunition;
import my_project.modes.travelMode.Ammunition.ElectricAmmunition;
import my_project.modes.travelMode.Ammunition.ExplosiveAmmunition;
import my_project.modes.travelMode.Ammunition.NormalAmmunition;
import my_project.settings.SettingsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tomole
 */
public class ShopWindow {
    private MainController mainController;

    private List<Ammunition> shopItems;
    private Stack<Ammunition> ammo;

    private JPanel shopPanal;
    private JButton discardButton;
    private JButton takeButton;
    private JLabel shopOption;
    private JLabel optionPicture;
    private JLabel priceLabel;
    private JLabel tippLabel;
    private JLabel playerMoney;
    private JLabel discardPrice;
    private JButton repairCarriageButton;
    private JLabel carriageHealth;
    private JLabel carriageRepairPriceLabel;

    private ImageIcon normalIcon = new ImageIcon("src/main/resources/graphic/NormalAmmo.png");
    private ImageIcon electricIcon = new ImageIcon("src/main/resources/graphic/ElektricAmmo.png");
    private ImageIcon explodingIcon = new ImageIcon("src/main/resources/graphic/ExplodingAmmo.png");

    private ImageIcon coinIcon = new ImageIcon("src/main/resources/graphic/Coin.png");
    private JFXPanel fxPanel = new JFXPanel();
    private Sound shopSound = new Sound("src/main/resources/sound/Buying.mp3","Buying", false);
    private Sound declineSound = new Sound("src/main/resources/sound/swoosh_decline.mp3","Decline", false);

    public ShopWindow(MainController mainController) {
        this.mainController = mainController;

        ammo = mainController.getCurrentPlayer().getAmmo();

        initiateShopQueue();
        updateShopText();

        playerMoney.setIcon(coinIcon);
        priceLabel.setIcon(coinIcon);
        discardPrice.setIcon(coinIcon);
        carriageRepairPriceLabel.setIcon(coinIcon);
        discardPrice.setText("2");

        normalIcon.setImage(normalIcon.getImage().getScaledInstance(24*3, 32*3, Image.SCALE_DEFAULT));
        electricIcon.setImage(electricIcon.getImage().getScaledInstance(24*3, 32*3, Image.SCALE_DEFAULT));
        explodingIcon.setImage(explodingIcon.getImage().getScaledInstance(24*3, 32*3, Image.SCALE_DEFAULT));

        discardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainController.getCurrentPlayer().getMoney() >= 2) {
                    shopSound.setVolume(SettingsModel.getMasterVolume());
                    if(shopSound.isPlaying()) shopSound.stop();
                    shopSound.play();

                    mainController.getCurrentPlayer().addMoney(-2);
                    shopItems.toFirst();
                    shopItems.remove();
                    addAmmunition();
                    updateShopText();
                }else showTipp();
            }
        });
        takeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopItems.toFirst();
                if(checkingToBuy(shopItems.getContent())) {
                    shopSound.setVolume(SettingsModel.getMasterVolume());
                    if(shopSound.isPlaying()) shopSound.stop();
                    shopSound.play();

                    Ammunition takenAmmunition = shopItems.getContent();
                    shopItems.remove();
                    addAmmunition();
                    updateShopText();
                    ammo.push(takenAmmunition);
                }
            }
        });
        repairCarriageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainController.getCurrentPlayer().getMoney() >= 30) {
                    mainController.getCurrentPlayer().addMoney(-30);
                    mainController.getCurrentPlayer().setCarHp(mainController.getCurrentPlayer().getCarHp() + 50);
                    shopSound.setVolume(SettingsModel.getMasterVolume());
                    if (shopSound.isPlaying()) shopSound.stop();
                    shopSound.play();
                    updateShopText();
                }else showTipp();
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
        declineSound.setVolume(SettingsModel.getMasterVolume());
        if(declineSound.isPlaying()) declineSound.stop();
        declineSound.play();
    }

    private void updateShopText() {
        shopItems.toFirst();
        String ammoType = "";
        if (shopItems.getContent() instanceof NormalAmmunition) {
            ammoType = "Normal Ammunition";
            optionPicture.setIcon(normalIcon);
            priceLabel.setText("10");
        }else if (shopItems.getContent() instanceof ElectricAmmunition) {
            ammoType = "Electric Ammunition";
            optionPicture.setIcon(electricIcon);
            priceLabel.setText("12");
        }else if (shopItems.getContent() instanceof ExplosiveAmmunition) {
            ammoType = "Explosive Ammunition";
            optionPicture.setIcon(explodingIcon);
            priceLabel.setText("15");
        }
        shopOption.setText(ammoType);
        playerMoney.setText(String.valueOf(mainController.getCurrentPlayer().getMoney()));

        carriageHealth.setText("Carriage HP: " + mainController.getCurrentPlayer().getCarHp() + " /500.0");
        carriageRepairPriceLabel.setText("30");
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
        shopItems.append(newAmmo);
    }

    private void initiateShopQueue() {
        shopItems = new List<Ammunition>();
        for (int i = 0; i < 10; i++) {
            addAmmunition();
        }
    }

    public JPanel getShopPane() {return shopPanal;}
}

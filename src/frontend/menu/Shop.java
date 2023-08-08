package frontend.menu;

import backend.network.request.Request;
import frontend.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Shop extends GameFrame {
    Timer timer;


    public Shop() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(false);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.WEST, gemCoinPanel());
        getContentPane().add(BorderLayout.EAST, itemsPanel());
        setVisible(true);
    }

    JPanel gemCoinPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel coins = new JLabel();
        JLabel gems = new JLabel();
        JTextField convertToCoins = new JTextField();
        JButton convert = new JButton("Convert");
        convert.addActionListener(e -> {
            try {
                int cnt = Integer.parseInt(convertToCoins.getText());
                Request.convertToCoins(cnt);
                convertToCoins.setText("");
            } catch (Exception ignored) {/**/}
        });

        timer = new Timer(3000, e -> {
            if (!isDisplayable()) {
                timer.stop();
                return;
            }
            coins.setText("Coins : " + Request.onlineCoins());
            gems.setText("Gems : " + Request.gems());
        });
        timer.setInitialDelay(0);
        timer.start();
        panel.add(coins);
        panel.add(gems);
        panel.add(convertToCoins);
        panel.add(convert);

        return panel;
    }

    JPanel itemsPanel() {

        return new JPanel();
    }
}

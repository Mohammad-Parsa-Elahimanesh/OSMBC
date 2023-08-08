package frontend.menu;

import backend.Manager;
import backend.network.request.BuyStatus;
import backend.network.request.Request;
import frontend.GameFrame;
import frontend.notification.Notification;
import frontend.notification.NotificationType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Shop extends GameFrame {
    Timer timer;


    public Shop() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        getContentPane().add(gemCoinPanel());
        getContentPane().add(itemsPanel());
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
        JPanel panel = new JPanel(new GridLayout(2,5));
        for(int i = 0; i < 10; i++)
            panel.add(item(i));
        return panel;
    }

    JButton item(int i) {
        JButton button = new JButton();
        try {
            Image background = ImageIO.read(new File("Images\\"+Manager.items[i][1]));
            button.setIcon(new ImageIcon(background));
        } catch (IOException e) {/**/}
        button.setPreferredSize(new Dimension(120,50));
        button.setMaximumSize(new Dimension(120,50));
        button.setSize(new Dimension(120,50));
        button.setToolTipText("<html>"+Manager.items[i][0]+"<br>"+ split(Manager.items[i][2])+"</html>");
        button.addActionListener(e -> {
            if(!Manager.isConnected()) {
                Notification.notice(NotificationType.ONLINE_ACCESS);
                return;
            }
            BuyStatus status = Request.buy(Manager.items[i][0]);
            switch (status) {
                case NOT_ENOUGH_LEVEL -> new Notification("Not Enough Level", "Your level must be increased to buy this!");
                case NOT_ENOUGH_GEMS -> new Notification("Not Enough gems", "Your gems must be increased to buy this!");
                case NOT_ENOUGH_COINS -> new Notification("More coins needed", "you don't have enough coins, you can convert your gems to coins from left panel");
                case ALL_PEOPLE_LIMIT -> new Notification("People are faster", "People buy all before you, check again later");
                case ONE_PERSON_LIMIT -> new Notification("Enough", "You can not buy more right now!");
                case SPECIFIC_TIME_LIMIT -> new Notification("Not Now", "You can not buy Item now, try in its available time!");
                case SUCCESS -> new Notification("Congratulations", "you buy item successfully!");
            }
        });
        return button;
    }

    private String split(String s) {
        String[] sp = s.split("   ");
        StringBuilder rs = new StringBuilder(sp[0]);
        for(int i = 1; i < sp.length; i++)
            rs.append("<br>").append(sp[i]);
        return rs.toString();
    }

}

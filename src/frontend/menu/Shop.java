package frontend.menu;

import backend.Manager;
import backend.network.request.Request;
import frontend.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        for(int i = 0; i < 10; i++) {
            JButton button = new JButton();

            try {
                Image background = ImageIO.read(new File("Images\\"+Manager.items[i][1]));
                button.setIcon(new ImageIcon(background));
            } catch (IOException e) {/**/}
            button.setPreferredSize(new Dimension(120,50));
            button.setMaximumSize(new Dimension(120,50));
            button.setSize(new Dimension(120,50));
            button.setToolTipText("<html>"+Manager.items[i][0]+"<br>"+ split(Manager.items[i][2])+"</html>");
            panel.add(button);
        }

        return panel;
    }
    private String split(String s) {
        String[] sp = s.split("   ");
        StringBuilder rs = new StringBuilder(sp[0]);
        for(int i = 1; i < sp.length; i++)
            rs.append("<br>").append(sp[i]);
        return rs.toString();
    }
}

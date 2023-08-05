package frontend.menu;

import backend.Manager;
import backend.offline.game_play.Record;

import javax.swing.*;

public class Profile extends JFrame {
    Profile() {
        setSize(500, 300);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2 + Manager.location.x, (Manager.SCREEN_HEIGHT - getHeight()) / 2 + Manager.location.y);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel top = new JLabel("name: " + Manager.currentUser().name + ", coins(offline): " + Manager.currentUser().offlineCoins);
        top.setSize(500, 80);
        top.setFont(Manager.PROFILE_TOPIC_FONT);
        panel.add(top);
        for (Record gameRecord : Manager.currentUser().records) {
            JLabel label = new JLabel("score: " + gameRecord.score + ", time: " + ((int) gameRecord.wholeTime) + ", killed enemies: " + gameRecord.killedEnemies);
            label.setSize(400, 60);
            label.setFont(Manager.PROFILE_RECORD_FONT);
            panel.add(label);
        }
        add(panel);
        setVisible(true);
    }
}


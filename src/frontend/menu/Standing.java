package frontend.menu;

import backend.Manager;
import backend.network.request.Request;
import frontend.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Standing extends GameFrame {
    final Color gold = new Color(255, 215, 0);
    final Color silver = new Color(192, 192, 192);
    final Color bronze = new Color(205, 127, 50);
    final Color me = new Color(16, 215, 146);

    Standing() {
        JPanel panel = new JPanel();
        String[] rankings = Request.standings();
        panel.setLayout(new GridLayout(Math.max(rankings.length, 5), 1));
        JLabel[] labels = new JLabel[rankings.length];
        for (int i = 0; i < rankings.length; i++) {
            labels[i] = new JLabel(rankings[i] + (" ".repeat(100)));
            if (i == 0) labels[i].setBackground(gold);
            else if (i == 1) labels[i].setBackground(silver);
            else if (i == 2) labels[i].setBackground(bronze);
            else if (rankings[i].contains(" " + Manager.currentUser().name + " ")) labels[i].setBackground(me);
            labels[i].setOpaque(true);
            labels[i].setFont(Manager.NOTIFICATION_TOPIC_FONT);
            labels[i].setPreferredSize(new Dimension(getWidth(), 60));
            panel.add(labels[i]);
        }
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(false);
        setVisible(true);
    }
}

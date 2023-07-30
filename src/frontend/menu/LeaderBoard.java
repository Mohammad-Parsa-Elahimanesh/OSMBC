package frontend.menu;

import backend.Manager;
import backend.offline.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeaderBoard extends JFrame {
    LeaderBoard() {
        setSize(800, 350);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2 + Manager.location.x, (Manager.SCREEN_HEIGHT - getHeight()) / 2 + Manager.location.y);
        ArrayList<User> topUsers = new ArrayList<>();
        for (User user : User.getUsers())
            if (user.maxRating != -1)
                topUsers.add(user);
        for (int i = 0; i < topUsers.size(); i++)
            for (int j = i + 1; j < topUsers.size(); j++)
                if (topUsers.get(i).maxRating < topUsers.get(j).maxRating) {
                    User temp = topUsers.get(i);
                    topUsers.set(i, topUsers.get(j));
                    topUsers.set(j, temp);
                }
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < topUsers.size() && i < 5; i++) {
            JLabel label = new JLabel((i + 1) + " : " + topUsers.get(i).maxRating + "   " + topUsers.get(i).name);
            label.setSize(800, 60);
            label.setFont(new Font("Arial", Font.PLAIN, 40));
            panel.add(label);
        }
        add(panel);
        setVisible(true);
    }
}

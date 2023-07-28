package frontend.menu;

import backend.Manager;

import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {
    Profile() {
        setSize(640, 360);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2 + Manager.location.x, (Manager.SCREEN_HEIGHT - getHeight()) / 2 + Manager.location.y);
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("name : " + Manager.currentUser().name));
        panel.add(new JLabel("max rating : " + Math.max(Manager.currentUser().maxRating, 0)));
        add(panel);
        setVisible(true);
    }
}

package frontend.menu;

import backend.Manager;
import backend.block.mario.Mario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Profile extends JFrame {
    Profile() {
        setSize(640, 360);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2, (Manager.SCREEN_HEIGHT - getHeight()) / 2);
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("name : " + Manager.getInstance().currentUser().name));
        panel.add(new JLabel("max rating : " + Math.max(Manager.getInstance().currentUser().maxRating, 0)));
        panel.add(new JLabel("available marios : " + commaSeparatedList(Manager.getInstance().currentUser().Marios())));
        add(panel);
        setVisible(true);
    }

    public static String commaSeparatedList(List<Mario> list) {
        StringBuilder ret = new StringBuilder(" ");
        for (int i = 0; i < list.size(); i++) {
            ret.append(list.get(i).getName());
            if (i + 1 != list.size())
                ret.append(", ");
        }
        return ret.toString();
    }
}

package frontend.menu.game;

import backend.Manager;
import frontend.menu.LoadGamePanel;

import javax.swing.*;
import java.awt.*;

public class PlayMenu extends JFrame {
    public PlayMenu() {
        super();
        JPanel panel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++)
            panel.add(new LoadGamePanel(i, this));
        setUndecorated(true);
        setSize(Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT);
        add(panel);
        setVisible(true);
    }

}

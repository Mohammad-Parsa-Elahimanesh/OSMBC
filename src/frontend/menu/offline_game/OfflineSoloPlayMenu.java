package frontend.menu.offline_game;

import frontend.GameFrame;
import frontend.menu.LoadGamePanel;

import javax.swing.*;
import java.awt.*;

public class OfflineSoloPlayMenu extends GameFrame {
    public OfflineSoloPlayMenu() {
        super();
        JPanel panel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++)
            panel.add(new LoadGamePanel(i, this));
        add(panel);
        setVisible(true);
    }

}

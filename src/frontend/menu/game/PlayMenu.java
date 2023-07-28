package frontend.menu.game;

import frontend.GameFrame;
import frontend.menu.LoadGamePanel;

import javax.swing.*;
import java.awt.*;

public class PlayMenu extends GameFrame {
    public PlayMenu() {
        super();
        JPanel panel = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++)
            panel.add(new LoadGamePanel(i, this));
        add(panel);
        setVisible(true);
    }

}

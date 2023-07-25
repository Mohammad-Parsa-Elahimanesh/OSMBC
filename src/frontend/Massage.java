package frontend;

import backend.Manager;

import javax.swing.*;

public class Massage extends JFrame {
    public Massage(String s) {
        super();
        setSize(300, 100);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2, (Manager.SCREEN_HEIGHT - getHeight()) / 2);
        add(new JLabel(s));
        setVisible(true);
    }
}

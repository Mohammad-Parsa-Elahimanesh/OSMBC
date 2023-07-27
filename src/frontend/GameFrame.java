package frontend;

import backend.Manager;

import javax.swing.*;

public class GameFrame extends JFrame {
    protected GameFrame() {
        setUndecorated(true);
        setSize(Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT);
        setLocation(Manager.location);
    }
}

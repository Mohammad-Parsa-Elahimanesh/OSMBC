package frontend.menu.game;

import backend.Manager;
import backend.gamePlay.Game;

import javax.swing.*;

public class SetGameSettings extends JFrame {
    public SetGameSettings() {
        Manager manager = Manager.getInstance();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JComboBox<Game.Difficulty> selectDifficulty = new JComboBox<>(Game.Difficulty.values());
        String[] marios = new String[manager.currentUser().Marios().toArray().length];
        for (int i = 0; i < marios.length; i++)
            marios[i] = manager.currentUser().Marios().get(i).getName();
        JComboBox<String> selectMario = new JComboBox<>(marios);
        JButton start = new JButton("Let's Start");
        start.addActionListener(e -> {
            try {
                manager.currentGame().constructor(manager.currentUser().Marios().get(selectMario.getSelectedIndex()), Game.Difficulty.values()[selectDifficulty.getSelectedIndex()]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            dispose();
        });
        panel.add(selectDifficulty);
        panel.add(selectMario);
        panel.add(start);
        add(panel);
        setUndecorated(true);
        setSize(Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT);
        setVisible(true);
    }
}

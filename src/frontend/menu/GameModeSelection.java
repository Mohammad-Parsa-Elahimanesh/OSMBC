package frontend.menu;

import backend.network.request.Request;
import frontend.GameFrame;
import frontend.Tools;

import javax.swing.*;

public class GameModeSelection extends GameFrame {
    enum PlayerMode {
        FRIENDLY,
        COMPETITION
    }
    final PlayerMode playerMode;
    GameModeSelection(PlayerMode playerMode) {
        super();
        this.playerMode = playerMode;
        JPanel panel = new JPanel(null);
        panel.add(adventure());
        panel.add(marathon());
        panel.add(survive());
        panel.add(back());
        add(panel);
        Request.updateOurUser();
        setVisible(true);
    }
    JButton adventure() {
        JButton adventure = Tools.tileButton(10, 1, 4, 2);
        adventure.setText("Adventure");
        adventure.addActionListener(e -> {
            // TODO
            dispose();
        });
        return adventure;
    }

    JButton marathon() {
        JButton marathon = Tools.tileButton(10, 5, 4, 2);
        marathon.setText("Marathon");
        marathon.addActionListener(e -> {
            // TODO
            dispose();
        });
        return marathon;
    }

    JButton survive() {
        JButton survive = Tools.tileButton(10, 9, 4, 2);
        survive.setText("Friendly");
        survive.addActionListener(e -> {
            // TODO
            dispose();
        });
        return survive;
    }
    JButton back() {
        JButton back = Tools.tileButton(10, 13, 4, 2);
        back.setText("Back");
        back.addActionListener(e -> {
            new PlayerModeSelection();
            dispose();
        });
        return back;
    }
}

package frontend.menu;

import backend.GameMode;
import backend.network.request.Request;
import frontend.GameFrame;
import frontend.Tools;
import frontend.menu.room.RoomSelection;

import javax.swing.*;

public class GameModeSelection extends GameFrame {
    final PlayerMode playerMode;

    public GameModeSelection(PlayerMode playerMode) {
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
        JButton adventure = Tools.tileButton(10, 1, 4, 2, "Adventure");
        adventure.addActionListener(e -> {
            dispose();
            nextStep(GameMode.ADVENTURE);
        });
        return adventure;
    }

    JButton marathon() {
        JButton marathon = Tools.tileButton(10, 5, 4, 2, "Marathon");
        marathon.addActionListener(e -> {
            dispose();
            nextStep(GameMode.MARATHON);
        });
        return marathon;
    }

    JButton survive() {
        JButton survive = Tools.tileButton(10, 9, 4, 2, "Survive");
        survive.addActionListener(e -> {
            dispose();
            nextStep(GameMode.SURVIVE);
        });
        return survive;
    }

    JButton back() {
        JButton back = Tools.tileButton(10, 13, 4, 2, "Back");
        back.addActionListener(e -> {
            new PlayerModeSelection();
            dispose();
        });
        return back;
    }

    void nextStep(GameMode mode) {
        if (playerMode == PlayerMode.FRIENDLY) {
            dispose();
            new RoomSelection(mode);
        } else {
            // TODO
        }
    }

    public enum PlayerMode {
        FRIENDLY,
        COMPETITION
    }

}

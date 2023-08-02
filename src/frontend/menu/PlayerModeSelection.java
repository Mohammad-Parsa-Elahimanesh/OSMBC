package frontend.menu;

import backend.network.request.Request;
import frontend.GameFrame;
import frontend.Tools;
import frontend.menu.offline_game.OfflineSoloPlayMenu;

import javax.swing.*;

public class PlayerModeSelection extends GameFrame {
    public PlayerModeSelection() {
        super();
        JPanel panel = new JPanel(null);
        panel.add(solo());
        panel.add(competition());
        panel.add(friendly());
        panel.add(back());
        add(panel);
        Request.updateOurUser();
        setVisible(true);
    }

    JButton solo() {
        JButton solo = Tools.tileButton(10, 1, 4, 2);
        solo.setText("Solo");
        solo.addActionListener(e -> {
            new OfflineSoloPlayMenu();
            dispose();
        });
        return solo;
    }

    JButton competition() {
        JButton competition = Tools.tileButton(10, 5, 4, 2);
        competition.setText("Competition");
        competition.addActionListener(e -> {
            new GameModeSelection(GameModeSelection.PlayerMode.COMPETITION);
            dispose();
        });
        return competition;
    }

    JButton friendly() {
        JButton friendly = Tools.tileButton(10, 9, 4, 2);
        friendly.setText("Friendly");
        friendly.addActionListener(e -> {
            new GameModeSelection(GameModeSelection.PlayerMode.FRIENDLY);
            dispose();
        });
        return friendly;
    }

    JButton back() {
        JButton back = Tools.tileButton(10, 13, 4, 2);
        back.setText("Back");
        back.addActionListener(e -> {
            new MainMenu();
            dispose();
        });
        return back;
    }
}

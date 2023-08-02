package frontend.menu;

import backend.Manager;
import backend.offline.game_play.Game;
import frontend.Tools;
import frontend.menu.offline_game.OfflineSoloPlayMenu;

import javax.swing.*;
import java.awt.*;

public class LoadGamePanel extends JPanel {
    final int indexGame;
    final OfflineSoloPlayMenu offlineSoloPlayMenu;

    public LoadGamePanel(int indexGameInit, OfflineSoloPlayMenu offlineSoloPlayMenuInit) {
        super();
        indexGame = indexGameInit;
        offlineSoloPlayMenu = offlineSoloPlayMenuInit;
        setLayout(new BorderLayout());
        JButton loadGame = Tools.tileButton(0, 0, 8, 12);
        loadGame.setText(Game.state(Manager.currentUser().game[indexGame]));
        loadGame.addActionListener(e -> {
            offlineSoloPlayMenu.dispose();
            Manager.currentUser().runGame(indexGame);
        });

        JButton clear = Tools.tileButton(0, 12, 8, 4);
        clear.setText("Clear");
        clear.addActionListener(e -> {
            Manager.currentUser().game[indexGame] = null;
            loadGame.setText(Game.state(null));
        });


        add(loadGame);
        JComponent clearComponent = new JComponent() {
        };
        add(clearComponent);
        clearComponent.add(clear);
    }

}

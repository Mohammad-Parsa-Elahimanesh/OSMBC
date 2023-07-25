package frontend.menu;

import backend.Manager;
import backend.gamePlay.Game;
import frontend.menu.game.PlayMenu;
import frontend.tile.TileButton;

import javax.swing.*;
import java.awt.*;

public class LoadGamePanel extends JPanel {
    final int indexGame;
    final PlayMenu playMenu;

    public LoadGamePanel(int indexGameInit, PlayMenu playMenuInit) {
        super();
        indexGame = indexGameInit;
        playMenu = playMenuInit;
        setLayout(new BorderLayout());
        TileButton loadGame = new TileButton();
        loadGame.setText(Game.state(Manager.getInstance().currentUser().game[indexGame]));
        loadGame.setTileSize(8, 12);
        loadGame.setTileLocation(0, 0);
        loadGame.addActionListener(e -> {
            playMenu.dispose();
            Manager.getInstance().currentUser().runGame(indexGame);
        });

        TileButton clear = new TileButton();
        clear.setTileSize(8, 4);
        clear.setTileLocation(0, 12);
        clear.setText("Clear");
        clear.addActionListener(e -> {
            Manager.getInstance().currentUser().game[indexGame] = null;
            loadGame.setText(Game.state(null));
        });


        add(loadGame);
        JComponent clearComponent = new JComponent() {
        };
        add(clearComponent);
        clearComponent.add(clear);
    }

}

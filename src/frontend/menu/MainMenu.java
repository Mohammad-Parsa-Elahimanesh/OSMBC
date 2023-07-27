package frontend.menu;

import backend.Manager;
import frontend.GameFrame;
import frontend.login.EnterPage;
import frontend.menu.game.PlayMenu;
import frontend.menu.shop.Shop;
import frontend.tile.TileButton;

import javax.swing.*;


public class MainMenu extends GameFrame {
    public MainMenu() {
        super();
        JPanel panel = new JPanel(null);
        panel.add(play());
        panel.add(shop());
        panel.add(profile());
        panel.add(leaderBoard());
        panel.add(back());
        add(panel);
        setVisible(true);
    }

    TileButton play() {
        TileButton play = new TileButton();
        play.setText("Play");
        play.setTileLocation(10, 1);
        play.setTileSize(4, 2);
        play.addActionListener(e -> {
            new PlayMenu();
            dispose();
        });
        return play;
    }

    TileButton shop() {
        TileButton shop = new TileButton();
        shop.setText("Shop");
        shop.setTileLocation(10, 4);
        shop.setTileSize(4, 2);
        shop.addActionListener(e -> {
            new Shop();
            dispose();
        });
        return shop;
    }

    TileButton profile() {
        TileButton profile = new TileButton();
        profile.setText("Profile");
        profile.setTileLocation(10, 7);
        profile.setTileSize(4, 2);
        profile.addActionListener(e -> new Profile());
        return profile;
    }

    TileButton leaderBoard() {
        TileButton leaderBoard = new TileButton();
        leaderBoard.setText("Leaderboard");
        leaderBoard.setTileLocation(10, 10);
        leaderBoard.setTileSize(4, 2);
        leaderBoard.addActionListener(e -> new LeaderBoard());
        return leaderBoard;
    }

    TileButton back() {
        TileButton back = new TileButton();
        back.setText("Back");
        back.setTileLocation(10, 13);
        back.setTileSize(4, 2);
        back.addActionListener(e -> {
            new EnterPage();
            dispose();
        });
        return back;
    }
}


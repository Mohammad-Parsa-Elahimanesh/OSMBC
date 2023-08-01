package frontend.menu;

import frontend.GameFrame;
import frontend.Tools;
import frontend.login.EnterPage;
import frontend.menu.game.PlayMenu;

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

    JButton play() {
        JButton play = Tools.tileButton(10, 1, 4, 2);
        play.setText("Play");
        play.addActionListener(e -> {
            new PlayMenu();
            dispose();
        });
        return play;
    }

    JButton shop() {
        JButton shop = Tools.tileButton(10, 4, 4, 2);
        shop.setText("Shop");
        shop.addActionListener(e -> {
            //new Shop();
            //dispose();
        });
        return shop;
    }

    JButton profile() {
        JButton profile = Tools.tileButton(10, 7, 4, 2);
        profile.setText("Profile");
        profile.addActionListener(e -> new Profile());
        return profile;
    }

    JButton leaderBoard() {
        JButton leaderBoard = Tools.tileButton(10, 10, 4, 2);
        leaderBoard.setText("Leaderboard");
        //leaderBoard.addActionListener(e -> new LeaderBoard());
        return leaderBoard;
    }

    JButton back() {
        JButton back = Tools.tileButton(10, 13, 4, 2);
        back.setText("Back");
        back.addActionListener(e -> {
            new EnterPage();
            dispose();
        });
        return back;
    }
}

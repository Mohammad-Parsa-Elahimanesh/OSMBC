package frontend.menu;

import backend.network.request.Request;
import frontend.GameFrame;
import frontend.Tools;
import frontend.login.EnterPage;

import javax.swing.*;


public class MainMenu extends GameFrame {
    public MainMenu() {
        super();
        JPanel panel = new JPanel(null);
        panel.add(play());
        panel.add(profile());
        panel.add(chat());
        panel.add(standing());
        panel.add(shop());
        panel.add(bags());
        panel.add(back());
        add(panel);
        Request.updateOurUser();
        setVisible(true);
    }

    JButton play() {
        JButton play = Tools.tileButton(7, 1, 4, 2);
        play.setText("Play");
        play.addActionListener(e -> {
            new PlayerModeSelection();
            dispose();
        });
        return play;
    }

    JButton profile() {
        JButton profile = Tools.tileButton(13, 1, 4, 2);
        profile.setText("Profile");
        profile.addActionListener(e -> new Profile());
        return profile;
    }

    JButton chat() {
        JButton chat = Tools.tileButton(7, 5, 4, 2);
        chat.setText("Chat");
        chat.addActionListener(e -> {
            // TODO
        });
        return chat;
    }

    JButton standing() {
        JButton standing = Tools.tileButton(13, 5, 4, 2);
        standing.setText("Standing");
        standing.addActionListener(e -> {
            // TODO
        });
        return standing;
    }

    JButton shop() {
        JButton shop = Tools.tileButton(7, 9, 4, 2);
        shop.setText("Shop");
        shop.addActionListener(e -> {
            // TODO
        });
        return shop;
    }

    JButton bags() {
        JButton bags = Tools.tileButton(13, 9, 4, 2);
        bags.setText("Bags");
        bags.addActionListener(e -> {
            // TODO
        });
        return bags;
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

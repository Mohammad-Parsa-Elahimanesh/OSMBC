package frontend.menu;

import backend.Manager;
import backend.network.request.Request;
import frontend.GameFrame;
import frontend.Tools;
import frontend.login.EnterPage;
import frontend.menu.pv_chat.ChatLists;
import frontend.notification.Notification;
import frontend.notification.NotificationType;

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
        JButton play = Tools.tileButton(7, 1, 4, 2, "Play");
        play.addActionListener(e -> {
            new PlayerModeSelection();
            dispose();
        });
        return play;
    }

    JButton profile() {
        JButton profile = Tools.tileButton(13, 1, 4, 2, "Profile");
        profile.addActionListener(e -> new Profile());
        return profile;
    }

    JButton chat() {
        JButton chat = Tools.tileButton(7, 5, 4, 2, "Chat");
        chat.addActionListener(e -> {
            dispose();
            new ChatLists();
        });
        return chat;
    }

    JButton standing() {
        JButton standing = Tools.tileButton(13, 5, 4, 2, "Standing");
        standing.addActionListener(e -> {
            if(Manager.isConnected()) {
                new Standing();
            } else {
                Notification.notice(NotificationType.ONLINE_ACCESS);
            }
        });
        return standing;
    }

    JButton shop() {
        JButton shop = Tools.tileButton(7, 9, 4, 2, "Shop");
        shop.addActionListener(e -> {
            // TODO
        });
        return shop;
    }

    JButton bags() {
        JButton bags = Tools.tileButton(13, 9, 4, 2, "Bags");
        bags.addActionListener(e -> {
            // TODO
        });
        return bags;
    }


    JButton back() {
        JButton back = Tools.tileButton(10, 13, 4, 2, "Back");
        back.addActionListener(e -> {
            new EnterPage();
            dispose();
        });
        return back;
    }
}

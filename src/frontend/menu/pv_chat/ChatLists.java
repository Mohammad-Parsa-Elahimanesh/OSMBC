package frontend.menu.pv_chat;

import backend.Manager;
import backend.User;
import backend.network.request.Request;
import frontend.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class ChatLists extends JFrame {
    ChatLists() {
        // TODO search to go!
        setSize(300, 800);
        setLocation(Manager.location);
        Request.users();
        JPanel panel = new JPanel(new GridLayout(Manager.currentUser().friends.length+1, 1));
        for(User user: Manager.currentUser().friends)
            panel.add(goPV(user));
        panel.add(back());
    }
    JButton goPV(User user) {
        JButton button = new JButton(user.name);
        button.addActionListener(e -> new PV(user));
        return button;
    }
    JButton back() {
        JButton button = new JButton("Back");
        button.addActionListener(e -> {
            dispose();
            new MainMenu();
        });
        return button;
    }
}

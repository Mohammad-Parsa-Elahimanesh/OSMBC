package frontend.menu.pv_chat;

import backend.Manager;
import backend.User;
import backend.network.request.Request;
import frontend.GameFrame;
import frontend.menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatLists extends GameFrame {
    JPanel mainPanel;
    User[] blockedFriends;
    User[] blockerFriends;

    public ChatLists() {
        blockedFriends = Request.blocked();
        blockerFriends = Request.blockers();
        mainPanel = usersPanel(Manager.currentUser().friends);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    JPanel usersPanel(User[] users) {
        JPanel panel = new JPanel(new GridLayout(users.length + 2, 1));
        for (User user : users)
            panel.add(userPanel(user));
        panel.add(search());
        panel.add(back());
        return panel;
    }

    JPanel userPanel(User user) {
        JPanel userPanel = new JPanel(new GridLayout(1, 3));

        JButton button = new JButton(user.name);
        button.addActionListener(e -> new PV(user));
        userPanel.add(button);

        String[] bs = {"Block", "Unblock"};
        JButton blockOrNot = new JButton(Arrays.asList(blockedFriends).contains(user) ? bs[1] : bs[0]);
        blockOrNot.addActionListener(e -> {
            if (blockOrNot.getText().equals(bs[0])) {
                Request.block(user);
                blockOrNot.setText(bs[1]);
            } else {
                Request.unblock(user);
                blockOrNot.setText(bs[0]);
            }
        });
        userPanel.add(blockOrNot);
        userPanel.add(new JLabel(Arrays.asList(blockerFriends).contains(user) ? "blocked" : "not blocked"));
        userPanel.setBackground(user.color());
        return userPanel;
    }

    JTextField search() {
        JTextField search = new JTextField();
        search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {/**/}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER && search.getCaretPosition() == search.getText().length()) {
                    blockedFriends = Request.blocked();
                    blockerFriends = Request.blockers();
                    List<User> searchedFriends = new ArrayList<>();
                    for (User friend : Manager.currentUser().friends)
                        if (friend.name.contains(search.getText()))
                            searchedFriends.add(friend);
                    getContentPane().removeAll();
                    mainPanel = usersPanel(searchedFriends.toArray(new User[0]));
                    getContentPane().add(mainPanel);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    revalidate();
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {/**/}
        });
        return search;
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

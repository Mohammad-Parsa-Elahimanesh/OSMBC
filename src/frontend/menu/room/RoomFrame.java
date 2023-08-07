package frontend.menu.room;

import backend.Manager;
import backend.network.request.Request;
import backend.online.room.AccessLevel;
import backend.online.room.Room;
import backend.online.room.RoomState;
import frontend.GameFrame;
import frontend.Tools;
import frontend.menu.Chats;
import frontend.menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RoomFrame extends GameFrame {
    final transient Room room;
    JPanel chatWindow = new JPanel();
    JPanel mainWindow = new JPanel();
    JTextArea newMassageArea = newSMS();
    Chats chatScrollPane = new Chats();
    JButton readyButton;
    JButton roomState;

    public RoomFrame(Room room) {
        this.room = room;
        getContentPane().setLayout(new BorderLayout());
        chatWindow.add(chatScrollPane);
        chatWindow.add(newMassageArea);
        getContentPane().add(chatWindow, BorderLayout.WEST);
        getContentPane().add(mainWindow, BorderLayout.EAST);
        completeMainWindow();
        completeChatWindow();
        revalidateAll();
    }

    private void completeChatWindow() {
        chatWindow.setPreferredSize(new Dimension(8*Manager.SINGLE_BLOCK_WIDTH, 16*Manager.SINGLE_BLOCK_HEIGHT));
        newMassageArea.setPreferredSize(new Dimension(8*Manager.SINGLE_BLOCK_WIDTH, 4*Manager.SINGLE_BLOCK_HEIGHT));
        chatScrollPane.setPreferredSize(new Dimension(8* Manager.SINGLE_BLOCK_WIDTH, 12*Manager.SINGLE_BLOCK_HEIGHT));
        chatScrollPane.setLocation(0, 0);
        newMassageArea.setLocation(0,12*Manager.SINGLE_BLOCK_HEIGHT);

    }
    private void completeMainWindow() {
        mainWindow.setPreferredSize(new Dimension(16*Manager.SINGLE_BLOCK_WIDTH, 16*Manager.SINGLE_BLOCK_HEIGHT));
        mainWindow.setLayout(new BorderLayout());
        mainWindow.add(buttons() ,BorderLayout.NORTH);
    }

    JPanel buttons() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1,4));
        buttonsPanel.add(userType());
        readyButton = readyOrUnready();
        buttonsPanel.add(readyButton);
        roomState = roomState();
        buttonsPanel.add(roomState);
        buttonsPanel.add(leave());

        return buttonsPanel;
    }
    JButton userType() {
        String[] gw = {"Gamer", "Watcher"};
        JButton userTyped = new JButton(gw[0]);
        userTyped.addActionListener(e -> {
            if(userTyped.getText().equals(gw[0])) {
                userTyped.setText(gw[1]);
                Request.changeRoleToWatcher();
            } else {
                userTyped.setText(gw[0]);
                Request.changeRoleToGamer();
            }
        });
        return userTyped;
    }
    JButton readyOrUnready() {
        String[] gw = {"Unready", "Ready"};
        JButton readyOrUnready = new JButton(gw[0]);
        readyOrUnready.addActionListener(e -> {
            if(readyOrUnready.getText().equals(gw[0]) && room.state == RoomState.CLOSE) {
                readyOrUnready.setText(gw[1]);
                Request.sayReady();
            } else {
                readyOrUnready.setText(gw[0]);
                Request.sayUnready();
            }
        });
        return readyOrUnready;
    }
    JButton roomState() {
        assert room != null;
        JButton roomStateB = new JButton(room.state.toString());
        roomStateB.addActionListener(e -> {
            if(room.getAccessLevel(Manager.currentUser()) == AccessLevel.BOSS)
            {
                if(room.state == RoomState.CLOSE)
                    Request.openRoom();
                else
                    Request.closeRoom();
            }
        });
        return roomStateB;
    }
    JButton leave() {
        JButton leave = new JButton("Leave");
        leave.addActionListener(e -> {
            Request.leaveRoom();
            room.end();
        });
        return leave;
    }
    public void frameUpdate() {
        chatScrollPane.updateChatScrollPane(room.chats);
        if(room.state != RoomState.CLOSE) readyButton.setText("Unready");
        roomState.setText(room.state.toString());

    }

    JTextArea newSMS() {
        JTextArea text = Tools.tileTextArea(0,12,8,4, "new massage");
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER && text.getCaretPosition() == text.getText().length()) {
                    Request.roomMassage(text.getText());
                    text.setText("new massage");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        return text;
    }


    void revalidateAll() {
        chatScrollPane.revalidate();
        newMassageArea.revalidate();
        chatWindow.revalidate();

        mainWindow.revalidate();
        revalidate();
    }
}

package frontend.menu.room;

import backend.GameMode;
import backend.User;
import backend.network.request.EnterRoomStatus;
import backend.network.request.Request;
import frontend.GameFrame;
import frontend.menu.GameModeSelection;
import frontend.notification.Notification;
import frontend.notification.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class RoomSelection extends GameFrame {
    final GameMode gameMode;
    final JPanel panel = new JPanel();
    final JScrollPane scrollPane = new JScrollPane();
    Timer timer;
    public RoomSelection(GameMode gameMode) {
        this.gameMode = gameMode;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPane);
        panel.add(buttons());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        timer = new Timer(3000, e -> {
            if(!isDisplayable())
                timer.stop();
            Map<User, Boolean> rooms = Request.rooms(gameMode);
            JPanel panelScroll = new JPanel(new GridLayout(rooms.size(), 1));
            for(Map.Entry<User, Boolean> entry : rooms.entrySet())
                panelScroll.add(roomPanel(entry.getKey(), entry.getValue()));
            scrollPane.setViewportView(panelScroll);
            System.out.println(rooms.size()+" "+scrollPane.getComponentCount());
        });
        timer.start();
        add(panel);
        setVisible(true);
    }
    JPanel buttons() {
        JPanel panelB = new JPanel(new GridLayout(1,2));
        panelB.add(newRoom());
        panelB.add(back());
        return panelB;
    }
    JButton back() {
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            dispose();
            new GameModeSelection(GameModeSelection.PlayerMode.FRIENDLY);
        });
        return back;
    }
    JPanel newRoom() {
        JPanel newRoom = new JPanel(new GridLayout(2,1));
        JTextField password = new JTextField("password(EmptyForNoPassword)");
        JButton newRoomButton = new JButton("New Room");
        newRoomButton.addActionListener(e -> {
            if(password.getText().contains(" ")) {
                Notification.notice(NotificationType.SPACE_IN_TEXT);
                return;
            }
            //dispose();
            Request.makeRoom(gameMode, password.getText().replace(" ", ""));
            // TODO
        });
        newRoom.add(newRoomButton);
        newRoom.add(password);
        return newRoom;
    }
    JPanel roomPanel(User creator, boolean lock) {
        JPanel enterRoomPanel = new JPanel(new GridLayout(1, lock?3:2));

        JLabel nameBoss = new JLabel(creator.name);
        JTextField password = new JTextField("password");
        JButton enter = new JButton("Enter");
        enter.addActionListener(e -> {
           EnterRoomStatus status = Request.enterRoom(creator, lock?password.getText():"");
           switch (status) {
               case INVALID_ROOM -> Notification.notice(NotificationType.INVALID_ROOM);
               case INACTIVE_ROOM -> Notification.notice(NotificationType.INACTIVE_ROOM);
               case INCORRECT_PASSWORD -> Notification.notice(NotificationType.INCORRECT_PASSWORD);
               case SUCCESS -> {
                   //dispose();
                   // TODO
               }
           }
        });

        enterRoomPanel.add(nameBoss);
        if(lock)enterRoomPanel.add(password);
        enterRoomPanel.add(enter);
        enterRoomPanel.setPreferredSize(new Dimension(100,100));
        enterRoomPanel.setBackground(creator.color());
        return enterRoomPanel;
    }

}

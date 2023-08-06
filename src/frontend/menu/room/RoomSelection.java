package frontend.menu.room;

import backend.GameMode;
import backend.User;
import backend.network.request.EnterRoomStatus;
import backend.network.request.Request;
import backend.online.room.Room;
import frontend.GameFrame;
import frontend.Tools;
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
        timer = new Timer(10000, e -> {
            if(!isDisplayable())
                timer.stop();
            Map<User, Boolean> rooms = Request.rooms(gameMode);
            JPanel panelScroll = new JPanel(new GridLayout(rooms.size(), 1));
            for(Map.Entry<User, Boolean> entry : rooms.entrySet())
                panelScroll.add(roomPanel(entry.getKey(), entry.getValue()));
            scrollPane.setViewportView(panelScroll);
        });
        timer.setInitialDelay(0);
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
        JButton back = Tools.tileButton(0,0,12,4, "Back");
        back.addActionListener(e -> {
            dispose();
            new GameModeSelection(GameModeSelection.PlayerMode.FRIENDLY);
        });
        return back;
    }
    JPanel newRoom() {
        JPanel newRoom = new JPanel(new GridLayout(2,1));
        JTextField password = Tools.tileTextField(0,2,12,2, "password(EmptyForNoPassword)");
        JButton newRoomButton = Tools.tileButton(0,0,12,2, "new Room");
        newRoomButton.addActionListener(e -> {
            if(password.getText().contains(" ")) {
                Notification.notice(NotificationType.SPACE_IN_TEXT);
                return;
            }
            dispose();
            String pass = password.getText().replace(" ", "");
            Request.makeRoom(gameMode, pass);
            new Room(pass);
        });
        newRoom.add(newRoomButton);
        newRoom.add(password);
        return newRoom;
    }
    JPanel roomPanel(User creator, boolean lock) {
        JPanel enterRoomPanel = new JPanel(new GridLayout(1, lock?3:2));

        JLabel nameBoss = Tools.tileLabel(0,0,1,lock?8:12, creator.name);
        JTextField password = Tools.tileTextField(0,0,1,lock?8:0, "password");
        JButton enter = Tools.tileButton(0,0,1,lock?8:12, "Enter");
        enter.addActionListener(e -> {
           EnterRoomStatus status = Request.enterRoom(creator, lock?password.getText():"");
           switch (status) {
               case INVALID_ROOM -> Notification.notice(NotificationType.INVALID_ROOM);
               case INACTIVE_ROOM -> Notification.notice(NotificationType.INACTIVE_ROOM);
               case INCORRECT_PASSWORD -> Notification.notice(NotificationType.INCORRECT_PASSWORD);
               case SUCCESS -> {
                   dispose();
                   new Room(password.getText());
               }
           }
        });

        enterRoomPanel.add(nameBoss);
        if(lock)enterRoomPanel.add(password);
        enterRoomPanel.add(enter);
        enterRoomPanel.setBackground(creator.color());
        return enterRoomPanel;
    }

}

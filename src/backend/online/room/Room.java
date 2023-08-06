package backend.online.room;

import backend.Manager;
import backend.User;
import backend.network.request.Request;
import backend.online.SMS;
import frontend.menu.MainMenu;
import frontend.menu.room.RoomFrame;
import frontend.notification.Notification;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Room {
    static final double DELAY = 1;
    RoomFrame frame = new RoomFrame();
    final String password;
    Map<User, AccessLevel> gamers = new HashMap<>();
    Map<User, AccessLevel> watchers = new HashMap<>();
    User[] kicked = new User[0];
    public SMS[] chats = new SMS[0];
    RoomState state = RoomState.OPEN;
    public Room(String password) {
        this.password = (password.length() == 0 ? null : password);
        frame.setVisible(true);
        updateInfo.start();
    }
    Timer updateInfo = new Timer((int) (DELAY * 1000), e -> {
        synchronized (Manager.connection) {
            Request.users();
            Request.friendInvitation();
            kicked = Request.kicked();
            for (User removed : kicked)
                if (removed.equals(Manager.currentUser())) {
                    new Notification("Kicked", "Sorry but room Managers kicked you from the room!");
                    end();
                    new MainMenu();
                    return;
                }
            state = Request.roomState();
            if (state == RoomState.FINISHED) {
                end();
                new MainMenu();
                return;
            }
            chats = Request.roomChats();
            gamers = Request.roomGamers();
            watchers = Request.roomWatchers();
            frame.frameUpdate(this);
        }
    });

    void end() {
        updateInfo.stop();
        // TODO set frame unvisiable
    }


    // TODO STATE ...
}

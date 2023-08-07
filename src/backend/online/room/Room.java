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
    final String password;
    public Map<User, AccessLevel> gamers = new HashMap<>();
    public Map<User, AccessLevel> watchers = new HashMap<>();
    public SMS[] chats = new SMS[0];
    public RoomState state = RoomState.OPEN;
    RoomFrame frame;
    User[] kicked = new User[0];

    public Room(String password) {
        this.password = (password.length() == 0 ? null : password);
        frame = new RoomFrame(this);
        frame.setVisible(true);
        updateInfo.start();
    }

    public AccessLevel getAccessLevel(User user) {
        return gamers.getOrDefault(user, watchers.getOrDefault(user, AccessLevel.USER));
    }    Timer updateInfo = new Timer((int) (DELAY * 1000), e -> {
        synchronized (Manager.connection) {
            Request.users();
            Request.friendInvitation();
            state = Request.roomState();
            if (state == RoomState.FINISHED) {
                end();
                return;
            }
            kicked = Request.kicked();
            for (User removed : kicked)
                if (removed.equals(Manager.currentUser())) {
                    end();
                    new Notification("Kicked", "Sorry but room Managers kicked you from the room!");
                    return;
                }
            chats = Request.roomChats();
            gamers = Request.roomGamers();
            watchers = Request.roomWatchers();
            frame.frameUpdate();
        }
    });

    public void end() {
        updateInfo.stop();
        frame.dispose();
        new MainMenu();
    }




    // TODO STATE ...
}

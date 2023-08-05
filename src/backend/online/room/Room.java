package backend.online.room;

import backend.Manager;
import backend.network.request.Request;
import backend.User;
import backend.online.SMS;
import frontend.menu.MainMenu;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Room {
    static final double DELAY =  1;
    Map<User, AccessLevel> gamers = new HashMap<>();
    Map<User, AccessLevel> watchers = new HashMap<>();
    User[] kicked = new User[0];
    SMS[] chats = new SMS[0];
    RoomState state = RoomState.WAIT;
    final String password;
    Timer updateInfo = new Timer((int)(DELAY * 1000), e -> {
        synchronized (Manager.connection) {
            Request.users();
            kicked = Request.kicked();
            for(User removed: kicked)
                if(removed.equals(Manager.currentUser())) {
                    end();
                    new MainMenu();
                    return;
                }
            state = Request.roomState();
            chats = Request.roomChats();
            gamers = Request.roomGamers();
            watchers = Request.roomWatchers();
        }
    });
    Room(String password) { // TODO NO SPACE IN PASSWORD
        this.password = (password.length() == 0?null:password);
    }
    void end() {
        updateInfo.stop();
        // TODO set frame unvisiable
    }
    // TODO STATE ...
}

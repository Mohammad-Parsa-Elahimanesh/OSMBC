package backend.network.request;

import backend.GameMode;
import backend.Manager;
import backend.User;
import backend.offline.game_play.Record;
import backend.online.SMS;
import backend.online.room.AccessLevel;
import backend.online.room.RoomState;
import frontend.notification.Notification;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private Request() {
    }

    public static void users() {
        Manager.connection.send(RequestType.USERS);
        int count = Manager.connection.nextInt();
        for (int i = 0; i < count; i++)
            new User(Manager.connection.next(), Manager.connection.nextInt());
        if(User.logedInUser != null)
            friends();
    }

    public static SignUpStatus signUp(String username, String password) {
        Manager.connection.send(RequestType.SIGN_UP + " " + username + " " + password);
        return SignUpStatus.valueOf(Manager.connection.next());
    }

    public static SignInStatus signIn(String username, String password) {
        Manager.connection.send(RequestType.SIGN_IN + " " + username + " " + password);
        return SignInStatus.valueOf(Manager.connection.next());
    }

    public static void updateOurUser() {
        if (Manager.isConnected()) {
            coins();
            records();
        }
    }

    public static void signOut() {
        if (Manager.isConnected() && User.logedInUser != null) {
            updateCoins();
            updateRecords();
            Manager.connection.send(RequestType.SIGN_OUT);
        }
        User.logedInUser = null;
    }

    public static void close() {
        if (Manager.isConnected()) {
            users();
            signOut();
            Manager.connection.send(RequestType.CLOSE);
            Manager.sleep(3000);
        }
        Manager.connection = null;
    }


    public static Map<User, Boolean> rooms(GameMode mode) {
        users();
        Manager.connection.send(RequestType.ROOMS + " " + mode);
        int count = Manager.connection.nextInt();
        Map<User, Boolean> rooms = new HashMap();
        for (int i = 0; i < count; i++)
            rooms.put(User.find(Manager.connection.next()), Manager.connection.next().equals("Y"));
        return rooms;
    }

    public static void makeRoom(GameMode mode, String password) {
        if (password == null)
            password = "";
        Manager.connection.send(RequestType.MAKE_ROOM + " " + mode + " " + password);
    }

    public static EnterRoomStatus enterRoom(User user, String password) {
        if (password == null) password = "";
        Manager.connection.send(RequestType.ENTER_ROOM + " " + user.name + " " + password);
        return EnterRoomStatus.valueOf(Manager.connection.next());
    }

    public static void roomMassage(String text) {
        Manager.connection.send(RequestType.ROOM_MASSAGE + " " + SMS.makeInLine(text));
    }

    public static void toBeFriend(User toBeFriend) {
        Manager.connection.send(RequestType.TO_BE_FRIEND + " " + toBeFriend.name);
    }

    public static void changeRoleToGamer() {
        Manager.connection.send(RequestType.CHANGE_ROLE_TO_GAMER);
    }

    public static void changeRoleToWatcher() {
        Manager.connection.send(RequestType.CHANGE_ROLE_TO_WATCHER);
    }

    public static void leaveRoom() {
        Manager.connection.send(RequestType.LEAVE_ROOM);
        // TODO, new menu, dispose room and ...
    }

    public static void sayReady() {
        Manager.connection.send(RequestType.SAY_READY);
    }

    public static void sayUnready() {
        Manager.connection.send(RequestType.SAY_UNREADY);
    }

    public static void kick(User user) {
        Manager.connection.send(RequestType.KICK + " " + user.name);
    }

    public static void openRoom() {
        Manager.connection.send(RequestType.OPEN_ROOM);
    }

    public static void closeRoom() {
        Manager.connection.send(RequestType.CLOSE_ROOM);
    }

    public static void setManager(User user) {
        Manager.connection.send(RequestType.SET_MANAGER + " " + user.name);
    }

    public static SMS[] roomChats() {
        Manager.connection.send(RequestType.ROOM_CHATS);
        int count = Manager.connection.nextInt();
        SMS[] chats = new SMS[count];
        for (int i = 0; i < count; i++)
            chats[i] = new SMS(User.find(Manager.connection.next()), SMS.makeRegular(Manager.connection.next()));
        return chats;
    }

    public static void friendInvitation() {
        Manager.connection.send(RequestType.FRIEND_INVITATION);
        String name = Manager.connection.next();
        User user = User.find(name);
        if (user == null) return;
        new Notification("Invitation", "User " + user.name + " like to be your friend! do you like too?", () -> toBeFriend(user));
    }

    public static User[] kicked() {
        Manager.connection.send(RequestType.KICKED);
        int count = Manager.connection.nextInt();
        User[] kicked = new User[count];
        for (int i = 0; i < count; i++) {
            String name = Manager.connection.next();
            kicked[i] = User.find(name);
        }
        return kicked;
    }

    public static Map<User, AccessLevel> roomWatchers() {
        Manager.connection.send(RequestType.ROOM_WATCHERS);
        Map<User, AccessLevel> watchers = new HashMap<>();
        int count = Manager.connection.nextInt();
        for (int i = 0; i < count; i++)
            watchers.put(User.find(Manager.connection.next()), AccessLevel.valueOf(Manager.connection.next()));
        return watchers;
    }

    public static Map<User, AccessLevel> roomGamers() {
        Manager.connection.send(RequestType.ROOM_GAMERS);
        Map<User, AccessLevel> gamers = new HashMap<>();
        int count = Manager.connection.nextInt();
        for (int i = 0; i < count; i++)
            gamers.put(User.find(Manager.connection.next()), AccessLevel.valueOf(Manager.connection.next()));
        return gamers;
    }

    public static RoomState roomState() {
        Manager.connection.send(RequestType.ROOM_STATE);
        return RoomState.valueOf(Manager.connection.next());
    }
    public static void sendMassage(User receiver, String text) {
        Manager.connection.send(RequestType.SEND_MASSAGE+" "+receiver.name+" "+SMS.makeInLine(text));
    }
    public static SMS[] getMassages(User user) {
        Manager.connection.send(RequestType.GET_MASSAGES+" "+user.name);
        int count = Manager.connection.nextInt();
        SMS[] chats = new SMS[count];
        for (int i = 0; i < count; i++)
            chats[i] = new SMS(User.find(Manager.connection.next()), SMS.makeRegular(Manager.connection.next()));
        return chats;
    }


    private static void friends() {
        Manager.connection.send(RequestType.FRIENDS);
        int count = Manager.connection.nextInt();
        User[] friends = new User[count];
        for (int i = 0; i < count; i++) {
            String name = Manager.connection.next();
            friends[i] = User.find(name);
        }
        Manager.currentUser().friends = friends;
    }

    private static void updateCoins() {
        Manager.connection.send(RequestType.UPDATE_OFFLINE_COINS + " " + Manager.currentUser().offlineCoins);
        Manager.currentUser().offlineCoins = 0;
    }

    private static void updateRecords() {
        User user = Manager.currentUser();
        StringBuilder recordsSTR = new StringBuilder();
        recordsSTR.append(user.records.size());
        for (Record gameRecord : user.records)
            recordsSTR.append(' ').append(gameRecord.score).append(' ').append((int) gameRecord.wholeTime).append(' ').append(gameRecord.killedEnemies);
        Manager.connection.send(RequestType.UPDATE_RECORDS + " " + recordsSTR);
    }

    private static void coins() {
        Manager.connection.send(RequestType.COINS);
        Manager.currentUser().offlineCoins += Manager.connection.nextInt();
    }

    private static void records() {
        Manager.connection.send(RequestType.RECORDS);
        int count = Manager.connection.nextInt();
        while (count-- > 0) {
            Record gameRecord = new Record();
            gameRecord.score = Manager.connection.nextInt();
            gameRecord.wholeTime = Manager.connection.nextInt();
            gameRecord.killedEnemies = Manager.connection.nextInt();
            if (!Manager.currentUser().records.contains(gameRecord))
                Manager.currentUser().records.add(gameRecord);
        }
    }
}

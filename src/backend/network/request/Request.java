package backend.network.request;

import backend.Manager;
import backend.offline.User;
import backend.offline.game_play.Record;

public class Request {
    private Request(){}
    public static void users() {
        Manager.connection.send(RequestType.USERS);
        int count = Manager.connection.scanner.nextInt();
        for(int i = 0; i < count; i++)
            new User(Manager.connection.scanner.next(), Manager.connection.scanner.nextInt());
    }
    public static SignUpStatus signUp(String username, String password) {
        Manager.connection.send(RequestType.SIGN_UP + " " + username + " " + password);
        return SignUpStatus.valueOf(Manager.connection.scanner.next());
    }
    public static SignInStatus signIn(String username, String password) {
        Manager.connection.send(RequestType.SIGN_IN+" "+username+" "+password);
        return SignInStatus.valueOf(Manager.connection.scanner.next());
    }
    public static void updateOurUser() {
        if(Manager.isConnected()) {
            coins();
            records();
        }
    }
    public static void signOut() {
        if(Manager.isConnected() && User.logedInUser != null) {
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


    private static void updateCoins() {
        Manager.connection.send(RequestType.UPDATE_OFFLINE_COINS +" "+Manager.currentUser().offlineCoins);
        Manager.currentUser().offlineCoins = 0;
    }
    private static void updateRecords() {
        User user = Manager.currentUser();
        StringBuilder recordsSTR = new StringBuilder();
        recordsSTR.append(user.records.size());
        for(Record gameRecord: user.records)
            recordsSTR.append(' ').append(gameRecord.score).append(' ').append((int)gameRecord.wholeTime).append(' ').append(gameRecord.killedEnemies);
        Manager.connection.send(RequestType.UPDATE_RECORDS+" "+recordsSTR);
    }
    private static void coins() {
        Manager.connection.send(RequestType.COINS);
        Manager.currentUser().offlineCoins += Manager.connection.scanner.nextInt();
    }
    private static void records() {
        Manager.connection.send(RequestType.RECORDS);
        int count = Manager.connection.scanner.nextInt();
        while (count-- > 0) {
            Record gameRecord = new Record();
            gameRecord.score = Manager.connection.scanner.nextInt();
            gameRecord.wholeTime = Manager.connection.scanner.nextInt();
            gameRecord.killedEnemies = Manager.connection.scanner.nextInt();
            if(!Manager.currentUser().records.contains(gameRecord))
                Manager.currentUser().records.add(gameRecord);
        }
    }
}

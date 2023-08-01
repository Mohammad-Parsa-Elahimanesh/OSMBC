package backend.network.request;

import backend.Manager;
import backend.offline.User;

public class Request {
    public static void users() {
        Manager.connection.send(RequestType.USERS);
        int count = Manager.connection.scanner.nextInt();
        User[] users = new User[count];
        for(int i = 0; i < count; i++)
        {
            String name = Manager.connection.scanner.next();
            int passwordHash = Manager.connection.scanner.nextInt();
            users[i] = new User(name, passwordHash);
        }
    }
    public static SignUpStatus signUp(String username, String password) {
        Manager.connection.send(RequestType.SIGN_UP + " " + username + " " + password);
        return SignUpStatus.valueOf(Manager.connection.scanner.next());
    }
    public static SignInStatus signIn(String username, String password) {
        Manager.connection.send(RequestType.SIGN_IN+" "+username+" "+password);
        return SignInStatus.valueOf(Manager.connection.scanner.next());
    }
    public static void signOut() {
        if(Manager.isConnected() && User.logedInUser != null) {
            // send coins, records
            Manager.connection.send(RequestType.SIGN_OUT);
        }
    }
    public static void updateUser() {
        Manager.connection.send(RequestType.UPDATE_USER);
        // get coins, records
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
}

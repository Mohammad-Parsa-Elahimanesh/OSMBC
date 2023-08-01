package backend.offline;

import backend.offline.game_play.Game;
import backend.offline.game_play.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private static final List<User> users = new ArrayList<>();
    public final List<Record> records = new ArrayList<>();
    public static User logedInUser;
    public final Game[] game = new Game[3];
    public final String name;
    public final int password;
    public int offlineCoins = 0;
    public int maxRating = -1;
    public int currentGameIndex = -1;
    public User(String name, int passwordHash) {
        this.name = name;
        this.password = passwordHash;
        if(!users.contains(this))
            users.add(this);
    }
    public User(String name, String password) {
        this(name, password.hashCode());
    }

    public static List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void runGame(int index) {
        currentGameIndex = index;
        if (game[index] == null)
            game[index] = new Game();
        else
            game[index].currentSection.timer.start();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return password == user.password && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}

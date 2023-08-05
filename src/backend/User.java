package backend;

import backend.offline.game_play.Game;
import backend.offline.game_play.Record;

import java.awt.*;
import java.util.*;
import java.util.List;

public class User {
    private static final List<User> users = new ArrayList<>();
    static final Color[] colors = {Color.green, Color.blue, Color.yellow, Color.red, Color.black};
    static final Map<User, Color> peopleColors = new HashMap<>();
    public static User logedInUser;
    public final List<Record> records = new ArrayList<>();
    public final Game[] game = new Game[3];
    public final String name;
    public final int password;
    public User[] friends = new User[0];
    public int offlineCoins = 0;
    public int currentGameIndex = -1;

    public User(String name, int passwordHash) {
        this.name = name;
        this.password = passwordHash;
        if (!users.contains(this)) {
            users.add(this);
            peopleColors.put(this, colors[peopleColors.size() % colors.length]);
        }

    }

    public User(String name, String password) {
        this(name, password.hashCode());
    }

    public static List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public static User find(String name) {
        for (User user : users)
            if (user.name.equals(name))
                return user;
        return null;
    }

    public void runGame(int index) {
        currentGameIndex = index;
        if (game[index] == null)
            game[index] = new Game();
        else
            game[index].currentSection.timer.start();
    }
    public Color color() {
        return peopleColors.get(this);
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

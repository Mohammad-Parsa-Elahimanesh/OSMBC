package backend.offline;

import backend.offline.game_play.Game;

public class User {
    public final Game[] game = new Game[3];
    public final String name;
    public final int password;
    public int coins = 0;
    public int maxRating = -1;
    public int currentGameIndex = -1;

    public User(String name, String password) {
        this.name = name;
        this.password = password.hashCode();
    }

    public void runGame(int index) {
        currentGameIndex = index;
        if (game[index] == null)
            game[index] = new Game();
        else
            game[index].currentSection.timer.start();
    }
}

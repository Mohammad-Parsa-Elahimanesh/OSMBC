package backend;

import backend.game_play.Game;

public class User {
    public final Game[] game = new Game[3];
    public final String name;
    public final String password;
    public int coins = 0;
    public int maxRating = -1;
    public int currentGameIndex = -1;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void runGame(int index) {
        currentGameIndex = index;
        if (game[index] == null)
            game[index] = new Game();
        else
            game[index].currentSection.timer.start();
    }
}

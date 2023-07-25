package backend;

import backend.block.Saleable;
import backend.block.mario.Mario;
import backend.block.mario.SimpleMario;
import backend.gamePlay.Game;

import java.util.ArrayList;
import java.util.List;

public class User {
    public final Game[] game = new Game[3];
    public final String name;
    public final String password;
    public final List<String> bought = new ArrayList<>();
    public int coins = 0;
    public int maxRating = -1;
    public int currentGameIndex = -1;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        buy(new SimpleMario());
    }

    public List<Mario> Marios() {
        List<Mario> marios = new ArrayList<>();
        for (String have : bought)
            for (Saleable mario : SuperMario.saleable)
                if ((mario instanceof Mario) && mario.getName().equals(have))
                    marios.add((Mario) mario);
        return marios;
    }

    public void buy(Saleable sold) {
        bought.add(sold.getName());
    }

    public void runGame(int index) {
        currentGameIndex = index;
        if (game[index] == null)
            game[index] = new Game();
        else
            game[index].currentSection.timer.start();
    }
}

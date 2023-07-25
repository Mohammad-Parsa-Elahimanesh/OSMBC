package backend;

import backend.block.mario.Mario;
import backend.game_play.Game;
import backend.game_play.Section;

import java.awt.*;

public class Manager { // TODO get a number as location! // TODO delete saber
    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    public static final int COLUMN = 24;
    public static final int SINGLE_BLOCK_WIDTH = SCREEN_WIDTH / COLUMN;
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    public static final int ROW = 16;
    public static final int SINGLE_BLOCK_HEIGHT = SCREEN_HEIGHT / ROW;
    private static final Manager singleton = new Manager();
    public SuperMario superMario;

    private Manager() {
    }

    public static void start() {
        singleton.superMario = new SuperMario();
    }

    public static Manager getInstance() {
        return singleton;
    }

    public User currentUser() {
        return superMario.currentUser;
    }

    public Game currentGame() {
        return currentUser().game[currentUser().currentGameIndex];
    }

    public Section currentSection() {
        return currentGame().currentSection;
    }

    public Mario currentMario() {
        return currentSection().mario;
    }


}

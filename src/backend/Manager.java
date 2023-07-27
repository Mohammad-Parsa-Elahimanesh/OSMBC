package backend;

import backend.block.mario.Mario;
import backend.game_play.Game;
import backend.game_play.Section;

import java.awt.*;

public class Manager {
    public static final int serverPort = 50000;

    public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 30 / 64;
    public static final int COLUMN = 24;
    public static final int SINGLE_BLOCK_WIDTH = SCREEN_WIDTH / COLUMN;
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 25 / 54;
    public static final int ROW = 16;
    public static final int SINGLE_BLOCK_HEIGHT = SCREEN_HEIGHT / ROW;
    public static Point location;
    public static SuperMario superMario;

    private Manager() {}

    public static void start(int locationPart) {
        location = new Point((locationPart&1)*(SCREEN_WIDTH+15), (locationPart/2)*(SCREEN_HEIGHT+15));
        superMario = new SuperMario();
    }

    public static User currentUser() {
        return superMario.currentUser;
    }

    public static Game currentGame() {
        return currentUser().game[currentUser().currentGameIndex];
    }

    public static Section currentSection() {
        return currentGame().currentSection;
    }

    public static Mario currentMario() {
        return currentSection().mario;
    }


}

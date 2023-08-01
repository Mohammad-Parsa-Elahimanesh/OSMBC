package backend;

import backend.network.Connection;
import backend.network.request.Request;
import backend.offline.User;
import backend.offline.block.mario.Mario;
import backend.offline.game_play.Game;
import backend.offline.game_play.Section;

import java.awt.*;
import java.io.IOException;

public class Manager {
    public static final int SERVER_PORT = 50000;
    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 30 / 64;
    public static final int COLUMN = 24;
    public static final int SINGLE_BLOCK_WIDTH = SCREEN_WIDTH / COLUMN;
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 25 / 54;
    public static final int ROW = 16;
    public static final int SINGLE_BLOCK_HEIGHT = SCREEN_HEIGHT / ROW;
    public static final Font NOTIFICATION_TOPIC_FONT = new Font(Font.SANS_SERIF, Font.BOLD, SCREEN_HEIGHT / 30);
    public static final Font NOTIFICATION_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, SCREEN_HEIGHT / 40);
    public static final Font PROFILE_TOPIC_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font PROFILE_RECORD_FONT = new Font("Arial", Font.PLAIN, 20);
    public static Point location;
    public static Connection connection;

    private Manager() {
    }

    public static void start(int locationPart) { // TODO read variable
        location = new Point((locationPart & 1) * (SCREEN_WIDTH + 15), (locationPart / 2) * (SCREEN_HEIGHT + 15));
    }

    public static User currentUser() {
        return User.logedInUser;
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

    public static boolean isConnected() {
        return connection != null;
    }

    public static boolean connect() {
        if (isConnected()) return true;
        for (int i = 0; i < 5; i++) {
            try {
                sleep(2000);
                connection = new Connection();
                Request.users();
                return true;
            } catch (IOException e) {
                connection = null;
            }
        }
        return false;
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {/**/}
    }

}

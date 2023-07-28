package backend;

import backend.network.Connection;
import backend.offline.SuperMario;
import backend.offline.User;
import backend.offline.block.mario.Mario;
import backend.offline.game_play.Game;
import backend.offline.game_play.Section;

import java.awt.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Manager {
    public static final int serverPort = 50000;
    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 30 / 64;
    public static final int COLUMN = 24;
    public static final int SINGLE_BLOCK_WIDTH = SCREEN_WIDTH / COLUMN;
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 25 / 54;
    public static final int ROW = 16;
    public static final int SINGLE_BLOCK_HEIGHT = SCREEN_HEIGHT / ROW;
    public static Point location;
    public static SuperMario superMario;
    static Connection connection;
    private Manager() {
    }

    public static void start(int locationPart) {
        location = new Point((locationPart & 1) * (SCREEN_WIDTH + 15), (locationPart / 2) * (SCREEN_HEIGHT + 15));
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
    public static boolean connect() {
        if(connection != null) return true;
        for(int i = 0; i < 5; i++) {
            try {
                Sleep(2000);
                connection = new Connection();
                // TODO update public datas
                return true;
            } catch (IOException e) {
                connection = null;
            }
        }
        return false;
    }
    public static void disconnect() {
        if(connection != null) {
            Sleep(2000);
            // TODO send massage
            // TODO sync data
        }
        connection = null;
    }

    static void Sleep(int milliseconds) {
        try {sleep(milliseconds);} catch (InterruptedException ignored){/**/}
    }

}

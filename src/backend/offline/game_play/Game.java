package backend.offline.game_play;

import backend.Manager;
import backend.offline.block.mario.Mario;
import frontend.menu.MainMenu;
import frontend.menu.game.GameFrame;


public class Game {
    public int score = 0;
    public GameFrame gameFrame = new GameFrame();
    public Section currentSection;

    public Game() {
        currentSection = Section.makeSections(new Mario());
        gameFrame.setVisible(true);
        currentSection.timer.start();
    }

    public static String state(Game game) {
        if (game == null)
            return "New Game";
        else
            return game.state();
    }

    void endGame() {
        if (Manager.currentUser().maxRating < score)
            Manager.currentUser().maxRating = score;
        Manager.currentUser().game[Manager.currentUser().currentGameIndex] = null;
        gameFrame.dispose();
        new MainMenu();
    }

    String state() {
        return "Section: " + currentSection;
    }

}

package backend.game_play;

import backend.Manager;
import backend.block.mario.Mario;
import frontend.menu.MainMenu;
import frontend.menu.game.GameFrame;


public class Game {
    public int score = 0;
    public GameFrame gameFrame = new GameFrame();
    public Section currentSection;
    Manager manager = Manager.getInstance();

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
        if (manager.currentUser().maxRating < score)
            manager.currentUser().maxRating = score;
        manager.currentUser().game[manager.currentUser().currentGameIndex] = null;
        gameFrame.dispose();
        new MainMenu();
    }

    String state() {
        return "Section: " + currentSection;
    }

}

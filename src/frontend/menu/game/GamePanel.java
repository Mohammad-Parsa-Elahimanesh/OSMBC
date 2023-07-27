package frontend.menu.game;

import backend.Manager;
import backend.block.Block;
import backend.game_play.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private final transient Manager manager = Manager.getInstance();
    transient Image backgroundImage;

    public GamePanel() {
        setLayout(null);
        try {
            backgroundImage = ImageIO.read(new File("Images\\background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintInfo(Graphics g) {
        int scale = Manager.SCREEN_WIDTH/30;
        g.setFont(new Font("Arial", Font.ITALIC, scale));
        g.setColor(Color.GREEN);
        g.drawString("Time : " + (int) (manager.currentSection().getWholeTime() - manager.currentSection().getSpentTime()), 0, scale);
        g.setColor(Color.RED);
        g.drawString("Heart: " + manager.currentMario().heart, 5*scale, scale);
        g.setColor(Color.BLUE);
        g.drawString(Game.state(manager.currentGame()), 10*scale, scale);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Score: " + manager.currentGame().score, 18*scale, scale);
        g.setColor(Color.YELLOW);
        g.drawString("Coins: " + manager.currentSection().getCoins(), 25*scale, scale);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT, this);
        final int cameraBeforeMario = 10;
        int cameraLeftLine = (int) (Manager.SINGLE_BLOCK_WIDTH * Math.min(Math.max(0, manager.currentMario().X - cameraBeforeMario), manager.currentSection().getLength() - Manager.COLUMN));
        paintInfo(g);
        for (Block block : manager.currentSection().blocks)
            block.draw(g, cameraLeftLine);
    }
}

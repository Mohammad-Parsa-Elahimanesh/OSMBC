package frontend.menu.game;

import backend.Manager;
import backend.block.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    private final transient Manager manager = Manager.getInstance();

    public GameFrame() {
        super();
        setContentPane(new GamePanel());
        setBackground(Color.cyan);
        setUndecorated(true);
        setSize(Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT);
        addKeyListener(keyListener());
    }

    KeyListener keyListener() {
        return new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_SPACE)
                    manager.currentMario().shot();
                if (manager.currentSection().timer.isRunning() && e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    manager.currentSection().timer.stop();
                    new ExitOrResume();
                }
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getExtendedKeyCode()) {
                    case KeyEvent.VK_LEFT -> manager.currentMario().task.put(Block.Direction.LEFT, true);
                    case KeyEvent.VK_UP -> manager.currentMario().task.put(Block.Direction.UP, true);
                    case KeyEvent.VK_RIGHT -> manager.currentMario().task.put(Block.Direction.RIGHT, true);
                    case KeyEvent.VK_DOWN -> manager.currentMario().task.put(Block.Direction.DOWN, true);
                }
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getExtendedKeyCode()) {
                    case KeyEvent.VK_LEFT -> manager.currentMario().task.put(Block.Direction.LEFT, false);
                    case KeyEvent.VK_UP -> manager.currentMario().task.put(Block.Direction.UP, false);
                    case KeyEvent.VK_RIGHT -> manager.currentMario().task.put(Block.Direction.RIGHT, false);
                    case KeyEvent.VK_DOWN -> manager.currentMario().task.put(Block.Direction.DOWN, false);
                }
            }
        };
    }

}

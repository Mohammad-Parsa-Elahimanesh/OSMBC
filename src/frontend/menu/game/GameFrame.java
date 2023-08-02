package frontend.menu.game;

import backend.Manager;
import backend.offline.block.Block;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends frontend.GameFrame {
    public GameFrame() {
        super();
        setContentPane(new GamePanel());
        setBackground(Color.cyan);
        addKeyListener(keyListener());
    }

    KeyListener keyListener() {
        return new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_SPACE)
                    Manager.currentMario().shot();
                if (Manager.currentSection().timer.isRunning() && e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    Manager.currentSection().timer.stop();
                    new ExitOrResume();
                }
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getExtendedKeyCode()) {
                    case KeyEvent.VK_LEFT -> Manager.currentMario().task.put(Block.Direction.LEFT, true);
                    case KeyEvent.VK_UP -> Manager.currentMario().task.put(Block.Direction.UP, true);
                    case KeyEvent.VK_RIGHT -> Manager.currentMario().task.put(Block.Direction.RIGHT, true);
                    case KeyEvent.VK_DOWN -> Manager.currentMario().task.put(Block.Direction.DOWN, true);
                }
                if(e.getExtendedKeyCode() == KeyEvent.VK_ADD)
                    Manager.currentMario().hack();
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getExtendedKeyCode()) {
                    case KeyEvent.VK_LEFT -> Manager.currentMario().task.put(Block.Direction.LEFT, false);
                    case KeyEvent.VK_UP -> Manager.currentMario().task.put(Block.Direction.UP, false);
                    case KeyEvent.VK_RIGHT -> Manager.currentMario().task.put(Block.Direction.RIGHT, false);
                    case KeyEvent.VK_DOWN -> Manager.currentMario().task.put(Block.Direction.DOWN, false);
                }
            }
        };
    }

}

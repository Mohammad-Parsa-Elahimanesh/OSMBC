package frontend.menu.game;

import backend.Manager;
import frontend.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class ExitOrResume extends JFrame {
    ExitOrResume() {
        Manager.getInstance().currentMario().reset();
        setSize(200, 100);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2, (Manager.SCREEN_HEIGHT - getHeight()) / 2);
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(3, 1));
        panel.add(resumeGame());
        panel.add(soundControl());
        panel.add(exitGame());
        setUndecorated(true);
        setVisible(true);
    }

    JButton exitGame() {
        JButton exitGame = new JButton("Exit Game");
        exitGame.addActionListener(e -> {
            new MainMenu();
            dispose();
        });
        exitGame.setToolTipText("Your progress will be saved !");
        return exitGame;
    }

    JButton soundControl() {
        JButton sound = new JButton(AudioPlayer.getInstance().silenceForever ? "turn on sound" : "turn off sound");
        sound.addActionListener(e -> {
            AudioPlayer.getInstance().silenceForever = !AudioPlayer.getInstance().silenceForever;
            sound.setText(AudioPlayer.getInstance().silenceForever ? "turn on sound" : "turn off sound");
        });
        return sound;
    }

    JButton resumeGame() {
        JButton resumeGame = new JButton("Continue ...");
        resumeGame.addActionListener(e -> {
            Manager.getInstance().currentSection().timer.start();
            dispose();
        });
        return resumeGame;
    }
}

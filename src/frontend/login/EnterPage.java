package frontend.login;

import backend.Manager;
import frontend.tile.TileButton;

import javax.swing.*;

public class EnterPage extends JFrame {
    public EnterPage() {
        super();
        JPanel panel = new JPanel(null);
        setUndecorated(true);

        setSize(Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT);
        panel.add(signInButton());
        panel.add(signUpButton());
        panel.add(exitButton());
        add(panel);
        setVisible(true);
    }

    TileButton signInButton() {
        TileButton signInButton = new TileButton();
        signInButton.setText("Sign In");
        signInButton.setTileLocation(10, 4);
        signInButton.setTileSize(4, 2);
        signInButton.addActionListener(e -> {
            new SignInPage();
            dispose();
        });
        return signInButton;
    }

    TileButton signUpButton() {
        TileButton signUpButton = new TileButton();
        signUpButton.setText("Sign Up");
        signUpButton.setTileLocation(10, 7);
        signUpButton.setTileSize(4, 2);
        signUpButton.addActionListener(e -> {
            new SignUpPage();
            dispose();
        });
        return signUpButton;
    }

    TileButton exitButton() {
        TileButton exitButton = new TileButton();
        exitButton.setText("Exit");
        exitButton.setTileLocation(10, 10);
        exitButton.setTileSize(4, 2);
        exitButton.addActionListener(e -> System.exit(0));
        return exitButton;
    }

}

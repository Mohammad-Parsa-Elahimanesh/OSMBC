package frontend.login;

import backend.Manager;
import backend.User;
import frontend.Massage;
import frontend.tile.TileButton;
import frontend.tile.TileTextField;

import javax.swing.*;

public class SignUpPage extends JFrame {
    TileTextField userName = userNameField();
    TileTextField password = passwordField();

    SignUpPage() {
        super();
        JPanel panel = new JPanel(null);
        setUndecorated(true);
        setSize(Manager.SCREEN_WIDTH, Manager.SCREEN_HEIGHT);
        panel.add(userName);
        panel.add(password);
        panel.add(enterButton());
        panel.add(backButton());
        add(panel);
        setVisible(true);
    }

    TileTextField userNameField() {
        TileTextField userNameField = new TileTextField();
        userNameField.setTileLocation(10, 5);
        userNameField.setTileSize(4, 1);
        userNameField.setText("User Name");
        return userNameField;
    }

    TileTextField passwordField() {
        TileTextField passwordField = new TileTextField();
        passwordField.setTileLocation(10, 7);
        passwordField.setTileSize(4, 1);
        passwordField.setText("Password");
        return passwordField;
    }

    TileButton enterButton() {
        TileButton enterButton = new TileButton();
        enterButton.setText("Enter");
        enterButton.setTileLocation(10, 9);
        enterButton.setTileSize(4, 1);
        enterButton.addActionListener(e -> {
            for (User user : Manager.getInstance().superMario.users)
                if (user.name.equals(userName.getText())) {
                    new Massage("user already exists");
                    return;
                }
            User user = new User(userName.getText(), password.getText());
            Manager.getInstance().superMario.users.add(user);
            SignInPage.signIn(user);
            dispose();
        });
        return enterButton;
    }

    TileButton backButton() {
        TileButton backButton = new TileButton();
        backButton.setText("Back");
        backButton.setTileLocation(10, 11);
        backButton.setTileSize(4, 1);
        backButton.addActionListener(e -> {
            new EnterPage();
            dispose();
        });
        return backButton;
    }
}

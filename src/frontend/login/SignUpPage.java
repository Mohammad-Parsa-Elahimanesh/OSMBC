package frontend.login;

import backend.Manager;
import backend.offline.User;
import frontend.GameFrame;
import frontend.Massage;
import frontend.Tools;

import javax.swing.*;

public class SignUpPage extends GameFrame {
    JTextField userName = userNameField();
    JTextField password = passwordField();

    SignUpPage() {
        super();
        JPanel panel = new JPanel(null);
        panel.add(userName);
        panel.add(password);
        panel.add(enterButton());
        panel.add(backButton());
        add(panel);
        setVisible(true);
    }

    JTextField userNameField() {
        JTextField userNameField = Tools.tileTextField(10, 5, 4, 1);
        userNameField.setText("User Name");
        return userNameField;
    }

    JTextField passwordField() {
        JTextField passwordField = Tools.tileTextField(10, 7, 4, 1);
        passwordField.setText("Password");
        return passwordField;
    }

    JButton enterButton() {
        JButton enterButton = Tools.tileButton(10, 9, 4, 1);
        enterButton.setText("Enter");
        enterButton.addActionListener(e -> {
            for (User user : Manager.superMario.users)
                if (user.name.equals(userName.getText())) {
                    new Massage("user already exists");
                    return;
                }
            User user = new User(userName.getText(), password.getText());
            Manager.superMario.users.add(user);
            SignInPage.signIn(user);
            dispose();
        });
        return enterButton;
    }

    JButton backButton() {
        JButton backButton = Tools.tileButton(10, 11, 4, 1);
        backButton.setText("Back");
        backButton.addActionListener(e -> {
            new EnterPage();
            dispose();
        });
        return backButton;
    }
}

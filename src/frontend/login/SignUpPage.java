package frontend.login;

import backend.network.request.Request;
import backend.network.request.SignUpStatus;
import backend.offline.User;
import frontend.GameFrame;
import frontend.notification.Notification;
import frontend.Tools;
import frontend.notification.NotificationType;

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
        userNameField.setText("UserName");
        return userNameField;
    }

    JTextField passwordField() {
        JTextField passwordField = Tools.tileTextField(10, 7, 4, 1);
        passwordField.setText("Password");
        return passwordField;
    }

    JButton enterButton() {
        JButton enterButton = Tools.tileButton(10, 9, 4, 1);
        enterButton.setText("Register");
        enterButton.addActionListener(e -> {
            SignUpStatus status = Request.signUp(userName.getText(), password.getText());
            switch (status) {
                case SUCCESS -> {
                    User user = new User(userName.getText(), password.getText());
                    SignInPage.signIn(user);
                    dispose();
                }
                case NON_UNIQUE_USERNAME -> Notification.notice(NotificationType.USER_EXIST);
                case SPACE_IN_TEXT -> Notification.notice(NotificationType.SPACE_IN_TEXT);
            }
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

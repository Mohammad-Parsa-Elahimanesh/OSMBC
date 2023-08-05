package frontend.login;

import backend.User;
import backend.network.request.Request;
import backend.network.request.SignUpStatus;
import frontend.GameFrame;
import frontend.Tools;
import frontend.notification.Notification;
import frontend.notification.NotificationType;

import javax.swing.*;

public class SignUpPage extends GameFrame {
    JTextField userName = Tools.tileTextField(10, 5, 4, 1, "UserName");
    JTextField password = Tools.tileTextField(10, 7, 4, 1, "Password");

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

    JButton enterButton() {
        JButton enterButton = Tools.tileButton(10, 9, 4, 1, "Register");
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
        JButton backButton = Tools.tileButton(10, 11, 4, 1, "Back");
        backButton.addActionListener(e -> {
            new EnterPage();
            dispose();
        });
        return backButton;
    }
}

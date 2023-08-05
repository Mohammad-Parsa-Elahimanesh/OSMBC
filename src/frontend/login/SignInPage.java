package frontend.login;

import backend.Manager;
import backend.User;
import backend.network.request.Request;
import backend.network.request.SignInStatus;
import frontend.GameFrame;
import frontend.Tools;
import frontend.menu.MainMenu;
import frontend.notification.Notification;
import frontend.notification.NotificationType;

import javax.swing.*;

public class SignInPage extends GameFrame {
    JTextField userName = Tools.tileTextField(10, 5, 4, 1, "UserName");
    JTextField password = Tools.tileTextField(10, 7, 4, 1, "Password");

    SignInPage() {
        super();
        JPanel panel = new JPanel(null);
        panel.add(userName);
        panel.add(password);
        panel.add(enterButton());
        panel.add(backButton());
        add(panel);
        setVisible(true);
    }

    static void signIn(User user) {
        User.logedInUser = user;
        new MainMenu();
    }

    JButton enterButton() {
        JButton enterButton = Tools.tileButton(10, 9, 4, 1, "Sign In");
        enterButton.addActionListener(e -> {
            if (Manager.isConnected())
                Request.users();
            for (User user : User.getUsers())
                if (user.name.equals(userName.getText())) {
                    if (user.password == password.getText().hashCode()) {
                        SignInStatus status = (Manager.isConnected() ? Request.signIn(userName.getText(), password.getText()) : SignInStatus.SUCCESS);
                        switch (status) {
                            case SUCCESS -> {
                                signIn(user);
                                dispose();
                            }
                            case USER_NOT_EXIST -> Notification.notice(NotificationType.USER_NOT_EXIST);
                            case SPACE_IN_TEXT -> Notification.notice(NotificationType.SPACE_IN_TEXT);
                            case PASSWORD_INCORRECT -> Notification.notice(NotificationType.INCORRECT_PASSWORD);
                        }
                    } else Notification.notice(NotificationType.INCORRECT_PASSWORD);
                    return;
                }
            if (Manager.isConnected()) Notification.notice(NotificationType.USER_NOT_EXIST);
            else Notification.notice(NotificationType.USER_NOT_FOUND);
        });
        return enterButton;
    }

    JButton backButton() {
        JButton backButton = Tools.tileButton(10, 11, 4, 1, "Back");
        backButton.addActionListener(e -> {
            dispose();
            new EnterPage();
        });
        return backButton;
    }
}

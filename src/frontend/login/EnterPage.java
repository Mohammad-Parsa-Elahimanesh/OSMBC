package frontend.login;

import backend.Manager;
import backend.network.request.Request;
import frontend.GameFrame;
import frontend.Tools;
import frontend.notification.Notification;
import frontend.notification.NotificationType;

import javax.swing.*;

public class EnterPage extends GameFrame {
    static final String[] states = {"Connected", "Connecting ...", "Disconnected", "Disconnecting ..."};
    JButton connection = serverConnection();

    public EnterPage() {
        super();
        JPanel panel = new JPanel(null);
        panel.add(connection);
        panel.add(signInButton());
        panel.add(signUpButton());
        panel.add(exitButton());
        add(panel);
        if (Manager.isConnected())
            Request.signOut();
        setVisible(true);
    }

    JButton serverConnection() {
        JButton serverConnection = Tools.tileButton(10, 2, 4, 2, Manager.isConnected() ? states[0] : states[2]);
        serverConnection.addActionListener(e -> {
            if (serverConnection.getText().equals(states[2])) {
                serverConnection.setText(states[1]);
                new Thread(() -> {
                    if (Manager.connect())
                        serverConnection.setText(states[0]);
                    else
                        serverConnection.setText(states[2]);
                }).start();
            } else if (serverConnection.getText().equals(states[0])) {
                serverConnection.setText(states[3]);
                new Thread(() -> {
                    Request.close();
                    serverConnection.setText(states[2]);
                }).start();
            }
        });
        return serverConnection;
    }

    JButton signInButton() {
        JButton signInButton = Tools.tileButton(10, 5, 4, 2, "Sign In");
        signInButton.addActionListener(e -> {
            new SignInPage();
            dispose();
        });
        return signInButton;
    }

    JButton signUpButton() {
        JButton signUpButton = Tools.tileButton(10, 8, 4, 2, "Sign Up");
        signUpButton.addActionListener(e -> {
            if (Manager.isConnected() && connection.getText().equals(states[0])) {
                new SignUpPage();
                dispose();
            } else {
                Notification.notice(NotificationType.ONLINE_ACCESS);
            }
        });
        return signUpButton;
    }

    JButton exitButton() {
        JButton exitButton = Tools.tileButton(10, 11, 4, 2, "Exit");
        exitButton.addActionListener(e -> System.exit(0));
        return exitButton;
    }

}

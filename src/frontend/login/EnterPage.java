package frontend.login;

import backend.Manager;
import frontend.GameFrame;
import frontend.Tools;

import javax.swing.*;

public class EnterPage extends GameFrame {
    public EnterPage() {
        super();
        JPanel panel = new JPanel(null);
        panel.add(serverConnection());
        panel.add(signInButton());
        panel.add(signUpButton());
        panel.add(exitButton());
        add(panel);
        setVisible(true);
    }

    JButton serverConnection() {
        final String[] states = {"Connected", "Connecting ...", "Disconnected", "Disconnecting ..."};
        JButton serverConnection = Tools.tileButton(10, 2, 4, 2);
        serverConnection.setText(states[2]);
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
                    Manager.disconnect();
                    serverConnection.setText(states[2]);
                }).start();
            }
        });
        return serverConnection;
    }

    JButton signInButton() {
        JButton signInButton = Tools.tileButton(10, 5, 4, 2);
        signInButton.setText("Sign In");
        signInButton.addActionListener(e -> {
            new SignInPage();
            dispose();
        });
        return signInButton;
    }

    JButton signUpButton() {
        JButton signUpButton = Tools.tileButton(10, 8, 4, 2);
        signUpButton.setText("Sign Up");
        signUpButton.addActionListener(e -> {
            new SignUpPage();
            dispose();
        });
        return signUpButton;
    }

    JButton exitButton() {
        JButton exitButton = Tools.tileButton(10, 11, 4, 2);
        exitButton.setText("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        return exitButton;
    }

}

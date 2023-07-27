package frontend.login;

import backend.Manager;
import backend.User;
import frontend.GameFrame;
import frontend.Massage;
import frontend.Tools;
import frontend.menu.MainMenu;

import javax.swing.*;

public class SignInPage extends GameFrame {
    JTextField userName = userNameField();
    JTextField password = passwordField();

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
        Manager.superMario.currentUser = user;
        new MainMenu();
    }

    JTextField userNameField() {
        JTextField userNameField = Tools.tileTextField(10,5,4,1);
        userNameField.setText("User Name");
        return userNameField;
    }

    JTextField passwordField() {
        JTextField passwordField = Tools.tileTextField(10,7,4,1);
        passwordField.setText("Password");
        return passwordField;
    }

    JButton enterButton() {
        JButton enterButton = Tools.tileButton(10,9,4,1);
        enterButton.setText("Enter");
        enterButton.addActionListener(e -> {
            for (User user : Manager.superMario.users)
                if (user.name.equals(userName.getText())) {
                    if (user.password == password.getText().hashCode()) {
                        signIn(user);
                        dispose();
                    } else {
                        new Massage("Password is Wrong !");
                    }
                    return;
                }
            new Massage("User not Found !");
        });
        return enterButton;
    }

    JButton backButton() {
        JButton backButton = Tools.tileButton(10,11,4,1);
        backButton.setText("Back");
        backButton.addActionListener(e -> {
            dispose();
            new EnterPage();
        });
        return backButton;
    }
}

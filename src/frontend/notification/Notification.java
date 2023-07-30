package frontend.notification;

import backend.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Notification extends JFrame {
    private static final int MAX_CHARS_INLINE_TEXT = 36;

    public Notification(String topic, String text, Runnable method) {
        super();
        add(notificationJPanel(topic, text));
        setUndecorated(true);
        setSize(200, 60);
        setLocation((Manager.SCREEN_WIDTH - getWidth()) / 2 + Manager.location.x, Manager.location.y);
        setAutoRequestFocus(true);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (method != null)
                        method.run();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    setVisible(false);
                }
            }
        });

        setVisible(true);
    }

    public Notification(String topic, String text) {
        this(topic, text, null);
    }

    JPanel notificationJPanel(String topic, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(topicJLabel(topic));
        panel.add(textJLabel(text));
        panel.setBackground(Color.GREEN);
        return panel;
    }

    JLabel topicJLabel(String topic) {
        JLabel topicLabel = new JLabel(topic);
        topicLabel.setFont(Manager.NOTIFICATION_TOPIC_FONT);
        return topicLabel;
    }

    JLabel textJLabel(String text) {
        text = htmlFormatStringMaker(text);
        JLabel topicLabel = new JLabel(text);
        topicLabel.setFont(Manager.NOTIFICATION_TEXT_FONT);
        return topicLabel;
    }

    private String htmlFormatStringMaker(String text) {
        if (text.contains("<html>") || text.length() <= MAX_CHARS_INLINE_TEXT) return text;
        return "<html>" + text.substring(0, MAX_CHARS_INLINE_TEXT) + "<br>" + text.substring(MAX_CHARS_INLINE_TEXT) + "</html>";
    }

    public static void notice(NotificationType type) {
        switch (type) {
            case ONLINE_ACCESS -> new Notification("Online Access", "You are offline now, You need to connect server to get access to this part!");
            case SPACE_IN_TEXT -> new Notification("Space in Text", "username and password can not have spaces in their fields");
            case USER_EXIST -> new Notification("user exists", "<html>user with this username already exists. <br> usernames must be unique </html>");
            case USER_NOT_FOUND -> new Notification("User not Found", "<html> user not found currently according to our database <br> you can connect to server and try again. </html>");
            case USER_NOT_EXIST -> new Notification("User Doesn't Exist", "User with this username does not exist yet, ypu must sign up first.");
            case INCORRECT_PASSWORD -> new Notification("Incorrect Password", "<html> your password is incorrect <br> you can try again. </html>");
        }
    }
}

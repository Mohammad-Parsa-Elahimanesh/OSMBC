package frontend;

import backend.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;

public class Notification extends JFrame {
    public Notification(String topic, String text, Runnable method) {
        super();
        add(notificationJPanel(topic, text));
        setUndecorated(true);
        setSize(200, 60);
        setLocation((Manager.SCREEN_WIDTH - getWidth())/2 + Manager.location.x, Manager.location.y);
        setAutoRequestFocus(true);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {dispose();}
        });
        addMouseListener(new MouseAdapter() {
                             @Override
                             public void mouseClicked(MouseEvent e) {
                                 if(SwingUtilities.isLeftMouseButton(e)) {
                                     if(method != null)
                                         method.run();
                                 } else if(SwingUtilities.isRightMouseButton(e)) {
                                     setVisible(false);
                                 }
                             }
                         });

                setVisible(true);
    }
    public Notification(String topic, String text) {this(topic, text, null);}
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
        JLabel topicLabel = new JLabel(text);
        topicLabel.setFont(Manager.NOTIFICATION_TEXT_FONT);
        return topicLabel;
    }
}

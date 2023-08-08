package frontend.menu.pv_chat;

import backend.Manager;
import backend.User;
import backend.network.request.Request;
import frontend.Tools;
import frontend.menu.Chats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PV extends JFrame {
    final User friend;
    JTextArea newMassageArea = newSMS();
    Chats chatScrollPane = new Chats();
    Timer timer;

    public PV(User user) {
        friend = user;
        setSize(8 * Manager.SINGLE_BLOCK_WIDTH + 10, 16 * Manager.SINGLE_BLOCK_HEIGHT + 30);
        setLocation(Manager.location);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(chatScrollPane);
        getContentPane().add(newMassageArea);
        completeChatWindow();
        revalidateAll();
        timer = new Timer(500, e -> {
            if (!isDisplayable()) timer.stop();
            else frameUpdate();
        });
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        timer.start();
    }

    private void completeChatWindow() {
        newMassageArea.setPreferredSize(new Dimension(8 * Manager.SINGLE_BLOCK_WIDTH, 4 * Manager.SINGLE_BLOCK_HEIGHT));
        chatScrollPane.setPreferredSize(new Dimension(8 * Manager.SINGLE_BLOCK_WIDTH, 12 * Manager.SINGLE_BLOCK_HEIGHT));
        chatScrollPane.setLocation(0, 0);
        newMassageArea.setLocation(0, 12 * Manager.SINGLE_BLOCK_HEIGHT);
    }


    public void frameUpdate() {
        chatScrollPane.updateChatScrollPane(Request.getMassages(friend));
        revalidateAll();
        repaint();
    }

    JTextArea newSMS() {
        JTextArea text = Tools.tileTextArea(0, 12, 8, 4, "new massage");
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {/**/}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER && text.getCaretPosition() == text.getText().length()) {
                    Request.sendMassage(friend, text.getText());
                    text.setText("new massage");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {/**/}
        });
        return text;
    }

    void revalidateAll() {
        chatScrollPane.revalidate();
        newMassageArea.revalidate();
        revalidate();
    }
}

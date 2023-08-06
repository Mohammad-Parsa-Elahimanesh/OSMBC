package frontend.menu.room;

import backend.Manager;
import backend.User;
import backend.network.request.Request;
import backend.online.SMS;
import backend.online.room.Room;
import frontend.GameFrame;
import frontend.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RoomFrame extends GameFrame {
    JPanel window = new JPanel();
    JTextArea newMassageArea = newSMS();
    JScrollPane chatScrollPane = Tools.tileScrollPane(0,0,8,12);
    JPanel scrollPanel = new JPanel();

    public RoomFrame() {
        getContentPane().setLayout(new BorderLayout());
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        window.add(chatScrollPane);
        window.add(newMassageArea);
        getContentPane().add(window, BorderLayout.WEST);
        window.setPreferredSize(new Dimension(8*Manager.SINGLE_BLOCK_WIDTH, 16*Manager.SINGLE_BLOCK_HEIGHT));
        newMassageArea.setPreferredSize(new Dimension(8*Manager.SINGLE_BLOCK_WIDTH, 4*Manager.SINGLE_BLOCK_HEIGHT));
        chatScrollPane.setPreferredSize(new Dimension(8* Manager.SINGLE_BLOCK_WIDTH, 12*Manager.SINGLE_BLOCK_HEIGHT));
        chatScrollPane.setViewportView(scrollPanel);
        chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        chatScrollPane.setLocation(0, 0);
        newMassageArea.setLocation(0,12*Manager.SINGLE_BLOCK_HEIGHT);
        revalidateAll();
    }

    public void frameUpdate(Room room) {
        updateChatScrollPane(room);
        revalidateAll();
        repaint();
    }

    JTextArea newSMS() {
        JTextArea text = Tools.tileTextArea(0,12,8,4, "new massage");
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER && text.getCaretPosition() == text.getText().length()) {
                    Request.roomMassage(text.getText());
                    text.setText("new massage");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        return text;
    }
    void updateChatScrollPane(Room room) {

        for(int i = scrollPanel.getComponentCount(); i < room.chats.length; i++) {
            scrollPanel.add(smsShower(room.chats[i]));
        }

    }

    static final int LINE_CAP = 30;

    JLabel smsShower(SMS sms) {
        String[] lines = new String[5];
        lines[0] = sms.user.name+" :    ";
        StringBuilder msg = new StringBuilder(sms.text);
        while (msg.length() < 4*LINE_CAP) msg.append(' ');
        for(int i = 1; i < lines.length; i++)
            lines[i] = msg.substring((i-1)*LINE_CAP, i*LINE_CAP);
        StringBuilder text = new StringBuilder("<html>");
        for(int i = 0; i < lines.length; i++) {
            text.append(lines[i]);
            if(i+1 < lines.length)
                text.append(" <br> ");
        }
        text.append("</html>");
        JLabel smsLabel = Tools.tileLabel(0,0,8,4, text.toString());
        smsLabel.setBackground(sms.user.color());
        smsLabel.setOpaque(true);
        return smsLabel;
    }
    void revalidateAll() {
        scrollPanel.revalidate();
        chatScrollPane.revalidate();
        newMassageArea.revalidate();
        window.revalidate();
        revalidate();
    }
}

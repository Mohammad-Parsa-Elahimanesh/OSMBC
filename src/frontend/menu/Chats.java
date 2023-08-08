package frontend.menu;

import backend.online.SMS;
import frontend.Tools;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chats extends JScrollPane {
    static final int LINE_CAP = 30;

    JPanel panel = new JPanel();

    public Chats() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setViewportView(panel);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void updateChatScrollPane(SMS[] chats) {
        for (int i = panel.getComponentCount(); i < chats.length; i++) {
            panel.add(smsShower(chats[i]));
        }
        repaint();
        panel.repaint();
    }

    JLabel smsShower(SMS sms) {
        boolean hide = sms.text.contains("#Block");
        String[] lines = new String[5];
        lines[0] = sms.user.name + " :    ";
        StringBuilder msg = new StringBuilder(sms.text);
        while (msg.length() < 4 * LINE_CAP) msg.append(' ');
        for (int i = 1; i < lines.length; i++)
            lines[i] = msg.substring((i - 1) * LINE_CAP, i * LINE_CAP);
        StringBuilder text = new StringBuilder("<html>");
        for (int i = 0; i < lines.length; i++) {
            text.append(lines[i]);
            if (i + 1 < lines.length)
                text.append(" <br> ");
        }
        text.append("</html>");
        JLabel smsLabel = Tools.tileLabel(0, 0, 8, 4, hide?"click on content to be visible":text.toString());
        smsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    smsLabel.setText(text.toString());
                    smsLabel.repaint();
                }
            }
        });
        smsLabel.setBackground(sms.user.color());
        smsLabel.setOpaque(true);
        return smsLabel;
    }
}

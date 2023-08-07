package frontend;

import backend.Manager;

import javax.swing.*;
import java.awt.*;

public class Tools {
    private Tools() {
    }

    public static JButton tileButton(int x, int y, int width, int height, String text) {
        JButton button = new JButton(text);
        button.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
        button.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
        return button;
    }

    public static JTextField tileTextField(int x, int y, int width, int height, String text) {
        JTextField textField = new JTextField(text);
        textField.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
        textField.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
        return textField;
    }

    public static JLabel tileLabel(int x, int y, int width, int height, String text) {
        JLabel label = new JLabel(text);
        label.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
        label.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
        label.setPreferredSize(new Dimension(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT));
        return label;
    }

    public static JTextArea tileTextArea(int x, int y, int width, int height, String text) {
        JTextArea textArea = new JTextArea();
        textArea.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
        textArea.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
        textArea.setText(text);
        return textArea;
    }

    public static JScrollPane tileScrollPane(int x, int y, int width, int height) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
        scrollPane.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
        return scrollPane;
    }
}

package frontend;

import backend.Manager;

import javax.swing.*;

public class Tools {
    private Tools() {
    }

    public static JButton tileButton(int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
        button.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
        return button;
    }

    public static JTextField tileTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
        textField.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
        return textField;
    }

}

package frontend.tile;

import backend.Manager;

import javax.swing.*;

public class TileTextField extends JTextField {
    public void setTileSize(int width, int height) {
        super.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
    }

    public void setTileLocation(int x, int y) {
        super.setLocation(x * Manager.SINGLE_BLOCK_WIDTH, y * Manager.SINGLE_BLOCK_HEIGHT);
    }
}

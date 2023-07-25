package frontend.tile;

import backend.Manager;

import javax.swing.*;

public class TileLabel extends JLabel {
    public void setTileSize(int width, int height) {
        super.setSize(width * Manager.SINGLE_BLOCK_WIDTH, height * Manager.SINGLE_BLOCK_HEIGHT);
    }
}

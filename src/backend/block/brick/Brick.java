package backend.block.brick;

import backend.block.Block;

import java.awt.*;

public class Brick extends Block {

    public Brick(double w, double h, double x, double y) {
        super(w, h, x, y);
    }

    @Override
    protected boolean pushed(Direction side) {
        return false;
    }

    @Override
    protected String getImageName() {
        return "Brick.png";
    }

    @Override
    public void draw(Graphics g, int cameraLeftLine) {
        if (W == 1 && H == 1) {
            super.draw(g, cameraLeftLine);
            return;
        }

        for (int i = (int) X; i < X + W; i++)
            for (int j = (int) Y; j < Y + H; j++)
                new Brick(1, 1, i, j).draw(g, cameraLeftLine);
    }
}

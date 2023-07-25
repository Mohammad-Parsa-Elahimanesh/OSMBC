package backend.block.item;

import backend.Manager;
import backend.block.Block;
import backend.block.mario.SimpleMario;
import backend.gamePlay.Section;

public class Mushroom extends Item {
    double old = 0;

    Mushroom(double x, double y) {
        super(x, y);
    }

    @Override
    public void update() {
        if (old < 3) {
            old += Section.delay;
            if (old > 3)
                vx = new SimpleMario().getSpeed() / 2.0;
        }
        super.update();
    }

    @Override
    protected boolean pushed(Block.Direction side) {
        if ((vx < 0 && side == Direction.LEFT) || (vx > 0 && side == Direction.RIGHT))
            vx *= -1;
        return neighbor(Manager.getInstance().currentMario());
    }

    @Override
    protected String getImageName() {
        return "mushroom.png";
    }
}

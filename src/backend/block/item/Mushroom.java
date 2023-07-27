package backend.block.item;

import backend.Manager;
import backend.block.Block;
import backend.block.mario.Mario;
import backend.game_play.Section;

public class Mushroom extends Item {
    double old = 0;

    Mushroom(double x, double y) {
        super(x, y);
    }

    @Override
    public void update() {
        if (old < 3) {
            old += Section.DELAY;
            if (old > 3)
                vx = new Mario().getSpeed() / 2.0;
        }
        super.update();
    }

    @Override
    protected boolean pushed(Block.Direction side) {
        if ((vx < 0 && side == Direction.LEFT) || (vx > 0 && side == Direction.RIGHT))
            vx *= -1;
        return neighbor(Manager.currentMario());
    }

    @Override
    protected String getImageName() {
        return "mushroom.png";
    }
}

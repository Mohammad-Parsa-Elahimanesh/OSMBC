package backend.block.item;

import backend.Manager;
import backend.block.Block;
import backend.block.mario.Mario;
import backend.game_play.Section;

public class Star extends Item {
    double old = 0, onGround = 0;

    Star(double x, double y) {
        super(x, y);
    }

    @Override
    public void update() {
        if (push(Block.Direction.DOWN) == 0 && old > 3) onGround += Section.DELAY;
        else onGround = 0;
        if (onGround > 1)
            vy += new Mario().getJumpSpeed() / 1.5;
        if (old < 3) {
            old += Section.DELAY;
            if (old >= 3)
                vx = new Mario().getSpeed() / 2.0;
        }

        super.update();
    }

    @Override
    protected boolean pushed(Block.Direction side) {
        if ((vx < 0 && side == Direction.LEFT) || (vx > 0 && side == Direction.RIGHT))
            vx *= -1;
        else if (side == Direction.UP && vy > 0)
            vy = 0;
        return neighbor(Manager.getInstance().currentMario());
    }

    @Override
    protected String getImageName() {
        return "Star.png";
    }
}

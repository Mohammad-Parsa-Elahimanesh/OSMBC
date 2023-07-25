package backend.block.enemy;

import backend.Manager;
import backend.block.mario.Mario;

public class Goomba extends Enemy {
    public Goomba(double x, double y) {
        super(0.8, 0.8, x, y);
        vx = new Mario().getSpeed() * 0.6;
    }

    @Override
    public void update() {
        double lastX = X;
        super.update();
        if (push(Direction.DOWN) > 0) {
            vx *= -1;
            X = lastX;
        }
    }

    int scoreWhenBeKilled() {
        return 1;
    }

    @Override
    protected String getImageName() {
        return "goomba.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        if (neighbor(Manager.getInstance().currentMario(), Direction.UP)) {
            Die();
        } else if ((vx < 0 && side == Direction.LEFT) || (vx > 0 && side == Direction.RIGHT))
            vx *= -1;
        return neighbor(Manager.getInstance().currentMario(), side) && side != Direction.UP;
    }
}

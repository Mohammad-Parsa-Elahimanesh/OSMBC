package backend.block.enemy;

import backend.Manager;
import backend.block.mario.Mario;
import backend.game_play.Section;

import static java.lang.Math.max;

public class Koopa extends Enemy {

    transient double freeze = 0.0;


    public Koopa(double x, double y) {
        super(1.8, 1, x, y);
        vx = getNormalSpeed();
    }

    private static double getNormalSpeed() {
        return new Mario().getSpeed() * 0.4;
    }

    int scoreWhenBeKilled() {
        return 2;
    }

    @Override
    public void update() {
        double lastX = X;
        super.update();
        if (push(Direction.DOWN) > 0) {
            vx *= -1;
            X = lastX;
        }
        freeze = max(freeze - Section.DELAY, 0);
        if (freeze == 0)
            vx = (vx < 0 ? -1 : 1) * getNormalSpeed();
        else
            vx = vx * 0.9;

    }

    @Override
    protected String getImageName() {
        return freeze > 0 ? "koopaShell.png" : "koopa.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        if (neighbor(Manager.currentMario(), Direction.UP)) {
            if (0 < freeze && freeze < 2.6) {
                Die();
            } else {
                vx = 3 * getNormalSpeed();
                freeze = 3;
            }
        } else if ((vx < 0 && side == Direction.LEFT) || (vx > 0 && side == Direction.RIGHT))
            vx *= -1;
        return neighbor(Manager.currentMario(), side) && side != Direction.UP && freeze == 0;
    }
}

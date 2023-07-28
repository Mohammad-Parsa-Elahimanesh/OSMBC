package backend.offline.block.enemy;

import backend.Manager;
import backend.offline.block.mario.Mario;

public class Spiny extends Enemy {
    public Spiny(double x, double y) {
        super(1.5, 1.3, x, y);
    }

    private static double getNormalSpeed() {
        return new Mario().getSpeed() * 0.5;
    }

    @Override
    int scoreWhenBeKilled() {
        return 3;
    }

    @Override
    public void update() {
        double lastX = X;
        super.update();
        if (push(Direction.DOWN) > 0) {
            vx *= -1;
            X = lastX;
        }
        if (Manager.currentMario().Y == Y && distance(Manager.currentMario()) < 4) {
            vx += (vx < 0 ? -1 : 1) * getNormalSpeed() * 0.1;
            if (inSide(Manager.currentMario(), Direction.LEFT))
                vx = -Math.abs(vx);
            else
                vx = Math.abs(vx);
        } else {
            vx = (vx * 4 + (vx < 0 ? -1 : 1) * getNormalSpeed()) / 5;
        }
    }

    @Override
    protected String getImageName() {
        return "spiny.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        if ((vx < 0 && side == Direction.LEFT) || (vx > 0 && side == Direction.RIGHT))
            vx *= -1;
        return neighbor(Manager.currentMario(), side);
    }
}

package backend.offline.block.brick;

import backend.Manager;

public class Spring extends Brick {
    public Spring(double x, double y) {
        super(1, 1, x, y);
    }

    @Override
    protected String getImageName() {
        return "spring.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        return neighbor(Manager.currentMario(), Direction.UP);
    }
}

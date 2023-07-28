package backend.offline.block.brick;

import backend.Manager;
import backend.offline.block.item.Coin;
import backend.offline.block.mario.MarioState;

public class Soft extends Brick {
    SoftType softType;

    public Soft(SoftType softType, int x, int y) {
        super(1, 1, x, y);
        this.softType = softType;
    }

    @Override
    protected String getImageName() {
        return "soft.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        if (neighbor(Manager.currentMario(), Direction.DOWN)) {
            Manager.currentGame().score++;
            if (softType == SoftType.Coin) {
                Manager.currentSection().add(new Coin(X, Y + 1));
                softType = SoftType.Simple;
            } else if (Manager.currentMario().state != MarioState.mini)
                remove();
        }
        return false;
    }
}

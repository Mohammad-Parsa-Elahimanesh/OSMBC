package backend.block.brick;

import backend.Manager;
import backend.block.item.Coin;
import backend.block.mario.MarioState;

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
        if (neighbor(Manager.getInstance().currentMario(), Direction.DOWN)) {
            Manager.getInstance().currentGame().score++;
            if (softType == SoftType.Coin) {
                Manager.getInstance().currentSection().add(new Coin(X, Y + 1));
                softType = SoftType.Simple;
            } else if (Manager.getInstance().currentMario().state != MarioState.mini)
                remove();
        }
        return false;
    }
}

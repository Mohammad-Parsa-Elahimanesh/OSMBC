package backend.offline.block.mario;

import backend.Manager;
import backend.offline.block.Block;
import backend.offline.block.enemy.Enemy;
import backend.offline.block.item.Item;
import backend.offline.game_play.Section;

public class Saber extends Block {
    double alive = 0.5;

    protected Saber(Mario mario) {
        super(2, 0.3, (mario.getDirection() == 1 ? mario.X + mario.W + 0.2 : mario.X - 2.3), mario.Y + 0.8);
        vx = 8.0 * mario.getDirection();
    }


    @Override
    public void update() {
        alive -= Section.DELAY;
        if (alive < 0) {
            vx *= -1;
            alive = 8;
        }
        for (Block block : Manager.currentSection().blocks)
            if (isIntersect(block)) {
                if (block instanceof Item)
                    continue;
                if (block instanceof Enemy)
                    ((Enemy) block).Die();
                remove();
                break;
            }
        X += vx * Section.DELAY;
    }

    @Override
    protected String getImageName() {
        return "saber.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        return true;
    }
}

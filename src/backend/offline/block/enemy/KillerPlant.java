package backend.offline.block.enemy;

import backend.Manager;
import backend.offline.block.Block;
import backend.offline.block.Pipe;

public class KillerPlant extends Enemy {
    static int[] Height = {0, 1, 2, 3, 2, 1, 0};
    Pipe home;

    public KillerPlant(Pipe home) {
        super(home.W, 0, home.X, home.Y + home.H);
        this.home = home;
    }

    @Override
    int scoreWhenBeKilled() {
        return 1;
    }

    @Override
    protected String getImageName() {
        return "KillerPlant.png";
    }

    @Override
    protected boolean pushed(Block.Direction side) {
        return true;
    }

    @Override
    public void update() {
        int H = ((int) Manager.currentSection().getSpentTime()) % Height.length;
        setShape(home.W, Height[H], home.X, home.Y + home.H);
        super.update();
    }
}

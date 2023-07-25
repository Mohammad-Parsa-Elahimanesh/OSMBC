package backend.block.enemy;

import backend.Manager;
import backend.block.Block;
import frontend.menu.game.AudioPlayer;

public abstract class Enemy extends Block {
    Enemy(double w, double h, double x, double y) {
        super(w, h, x, y);
    }

    @Override
    protected boolean doesGravityAffects() {
        return true;
    }

    abstract int scoreWhenBeKilled();

    public void Die() {

        AudioPlayer.getInstance().playSound("enemyDeath");
        Manager.getInstance().currentSection().getCoin(3);
        Manager.getInstance().currentGame().score += scoreWhenBeKilled();
        remove();
    }
}

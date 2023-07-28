package backend.offline.block;

import backend.Manager;
import backend.offline.block.enemy.KillerPlant;
import backend.offline.game_play.Section;

public class Pipe extends Block {
    public Section destination = null;

    public Pipe(double x, double h, boolean hasPlant, Section section) {
        super(2, h, x, 0);
        section.add(this);
        if (hasPlant)
            section.add(new KillerPlant(this));
    }

    @Override
    protected String getImageName() {
        return "Pipe.png";
    }

    @Override
    protected boolean pushed(Direction side) {
        if (neighbor(Manager.currentMario(), Direction.UP) && Manager.currentMario().isDirection(Direction.DOWN) && destination != null) {
            Manager.currentSection().timer.stop();
            Manager.currentMario().Y += 0.5;
            destination.mario.state = Manager.currentMario().state;
            destination.mario.heart = Manager.currentMario().heart;
            Manager.currentGame().currentSection = destination;
            destination = null;
            Manager.currentSection().timer.start();
        }

        return false;
    }
}
